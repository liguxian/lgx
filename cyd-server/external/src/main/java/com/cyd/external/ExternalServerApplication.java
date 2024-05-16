package com.cyd.external;

import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.CydCodec;
import com.iohao.game.external.core.ExternalServer;

public class ExternalServerApplication {

    public static void main(String[] args) {
        // 对外开放的端口
        int externalPort = 10100;

        // 构建游戏对外服
        ExternalServer externalServer = new GameExternal().createExternalServer(externalPort);

        // 启动游戏对外服
        externalServer.startup();
    }
}
