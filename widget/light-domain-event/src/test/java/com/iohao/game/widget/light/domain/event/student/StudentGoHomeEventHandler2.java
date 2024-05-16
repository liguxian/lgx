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
package com.iohao.game.widget.light.domain.event.student;

import com.iohao.game.widget.light.domain.event.annotation.DomainEvent;
import com.iohao.game.widget.light.domain.event.message.DomainEventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 学生回家事件
 *
 * @author 渔民小镇
 * @date 2021-12-26
 */
@Slf4j
@DomainEvent
public final class StudentGoHomeEventHandler2 implements DomainEventHandler<StudentEo> {
    @Override
    public void onEvent(StudentEo studentEo, boolean endOfBatch) {
        log.debug("学生回家: {} , {}", studentEo, endOfBatch);
    }
}

