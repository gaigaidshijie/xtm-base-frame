package com.xtm.id.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * <p>Description:[] </p>
 * Created on : 2020/8/18 16:02
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@ConfigurationProperties(prefix = "xtm.leaf")
public class LeafProperties {
    private Snowflake snowflake;

    public static class Snowflake{
        private boolean enable =false;
        private String address;
        private int port;
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isEnable() {
            return enable;
        }
        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        @Override
        public String toString() {
            return "Snowflake{" +
                    "enable=" + enable +
                    '}';
        }
    }


    public Snowflake getSnowflake() {
        return snowflake;
    }

    public void setSnowflake(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public String toString() {
        return "LeafSpringBootProperties{" +
                ", snowflake=" + snowflake +
                '}';
    }
}
