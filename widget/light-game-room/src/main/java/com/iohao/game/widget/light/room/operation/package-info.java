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
/**
 * 房间内的玩法操作业务
 * <p>
 * 玩法操作业务 - 设计模式: 策略模式 + 享元模式
 * <pre>
 *     策略模式:
 *         定义一个接口，在写两个实现类并实现这个接口，这样就可以使用一个接口，在需要的时候，在根据情况使用哪一个实现类
 *     享元模式:
 *         维护 玩法接口的实现类实例 {@link com.iohao.game.widget.light.room.operation.OperationHandler}
 *
 *         将许多"虚拟"对象的状态集中管理, 减少运行时对象实例个数，节省内存
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-03-31
 */
package com.iohao.game.widget.light.room.operation;