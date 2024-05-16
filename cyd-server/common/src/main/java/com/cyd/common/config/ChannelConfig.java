package com.cyd.common.config;

import cn.hutool.core.util.StrUtil;
import com.cyd.common.entity.ChannelEntity;
import lombok.experimental.UtilityClass;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class ChannelConfig {

    public Map<String, ChannelEntity> channelMap= new ConcurrentHashMap<>();

    static {
        init();
    }

    public void init() {
        String path = "D:\\project\\gameserver\\cydserver\\common\\src\\main\\resources\\config\\ChannelConfig.xml";

        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件, 获取Document对象。
            Document document = reader.read(new File(path));
            // 通过document对象获取根节点bookstore
            Element root = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = root.elementIterator();
            // 遍历迭代器，获取根节点中的信息
            while (it.hasNext()) {
                Element channel = (Element) it.next();
                // 获取channel的属性名以及 属性值
                String channelId = channel.attributeValue("channelId");
                String innerVersion = channel.attributeValue("innerVersion");
                String sourceVersion = channel.attributeValue("sourceVersion");
                String sourceUrl = channel.attributeValue("sourceUrl");
                String rechargeUrl = channel.attributeValue("rechargeUrl");
                String payServerNo = channel.attributeValue("payServerNo");
                String tdAppId = channel.attributeValue("tdAppId");
                String isOpenTd = channel.attributeValue("isOpenTd");
                channelMap.put(channelId + "_" + innerVersion, new ChannelEntity()
                        .setChannelId(channelId)
                        .setInnerVersion(innerVersion)
                        .setSourceVersion(sourceVersion)
                        .setSourceUrl(sourceUrl)
                        .setRechargeUrl(rechargeUrl)
                        .setPayServerNo(StrUtil.isNotBlank(payServerNo) ? Integer.parseInt(payServerNo) : null)
                        .setTdAppId(tdAppId)
                        .setIsOpenTd(StrUtil.isNotBlank(isOpenTd) ? Boolean.valueOf(isOpenTd) : null)
                );
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public ChannelEntity get(String channelId, String innerVersion) {
        return channelMap.get(channelId + "_" + innerVersion);
    }
}
