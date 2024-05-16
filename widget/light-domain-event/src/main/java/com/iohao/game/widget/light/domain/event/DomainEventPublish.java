/*
 * ioGame
 * Copyright (C) 2021 - 2023  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.game.widget.light.domain.event;

import com.iohao.game.widget.light.domain.event.disruptor.DomainEventSource;
import com.iohao.game.widget.light.domain.event.disruptor.EventDisruptor;
import com.iohao.game.widget.light.domain.event.message.Topic;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * 事件发布器
 *
 * @author 渔民小镇
 * @date 2021-12-26
 */
@UtilityClass
public class DomainEventPublish {

    /**
     * 发送领域事件，这个方法是不会提供返回值的
     *
     * @param domainSource 领域消息
     */
    public void send(final DomainEventSource domainSource) {
        publishDomainEvent(domainSource, domainSource.getTopic(), true);
    }

    /**
     * 普通对象
     * <pre>
     *     发送领域事件
     * </pre>
     *
     * @param domainSource 领域消息
     */
    public void send(final Object domainSource) {
        if (domainSource instanceof DomainEventSource domainEventSource) {
            send(domainEventSource);
        } else {
            // 获取主题
            Class<?> topic = domainSource instanceof Topic ? ((Topic) domainSource).getTopic() : domainSource.getClass();
            publishDomainEvent(domainSource, topic, false);
        }
    }

    private void publishDomainEvent(final Object domainSource, Class<?> topic, boolean eventSource) {

        // 查找 DomainEvent 对应的 disruptor, 通过事件主题（类信息）获取事件处理 ringBuffers
        final Disruptor<EventDisruptor> disruptor = DisruptorManager.me().getDisruptor(topic);

        if (Objects.isNull(disruptor)) {
            throw new NullPointerException("没有配置处理 : " + topic + " 的领域事件. 请配置");
        }

        // 环形数组
        final RingBuffer<EventDisruptor> ringBuffer = disruptor.getRingBuffer();
        final long sequence = ringBuffer.next();

        try {
            /*
             * 用上面sequence的索引取出一个空的事件用于填充，可以重复使用是因为得益于环形数组
             * 这样避免了重复创建对象的消耗
             */
            final EventDisruptor eventDisruptor = ringBuffer.get(sequence);

            // 设置领域事件
            if (eventSource) {
                eventDisruptor.setDomainEventSource((DomainEventSource) domainSource);
            } else {
                eventDisruptor.setValue(domainSource);
            }
        } finally {
            //发布事件
            ringBuffer.publish(sequence);
        }
    }
}
