package com.iohao.game.action.skeleton.core.codec;

import com.iohao.game.common.Utils.BitUtil;
import com.iohao.game.common.Utils.XXTEAUtil;
import com.iohao.game.common.Utils.ZlibUtil;
import com.iohao.game.common.consts.CommonConst;
import com.iohao.game.common.kit.ProtoKit;
import org.apache.commons.lang.ArrayUtils;

import java.util.Objects;

public class CydCodec implements DataCodec {

    @Override
    public byte[] encode(Object data) {


        return new byte[0];
    }

    @Override
    public <T> T decode(byte[] data, Class<?> dataClass) {
        if (Objects.isNull(data)) {
            return (T) ProtoKit.parseProtoByte(CommonConst.emptyBytes, dataClass);
        }
        //消息头
        byte header = data[0];
        //是否压缩
        Boolean isCompress = BitUtil.GetBit(header, 0) == 1;
        //是否加密
        Boolean isEncrypt = BitUtil.GetBit(header, 1) == 1;

        data = ArrayUtils.remove(data, 0);

        if(isEncrypt) {
            //解密
            data = XXTEAUtil.Decrypt(data);
        }

        if(isCompress) {
            //解压
            data = ZlibUtil.decompress(data);
        }

        return (T) ProtoKit.parseProtoByte(data, dataClass);
    }

    @Override
    public String codecName() {
        return DataCodec.super.codecName();
    }
}
