package com.ne.notification.util;

public class EnumBean {
    public enum MessageType {
        TELEGRAM("TELEGRAM"),
        TELEGRAM_BOT("TELEGRAM_BOT"),
        SMS("SMS"),
        SMTP("SMTP"),
        WHATSAPP("WHATSAPP"),
        VOICE("VOICE"),
        PUSH("PUSH"),;
        private final String messageType;

        MessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageType() {
            return this.messageType;
        }
    }

    public enum ChannelType {
        TELEGRAM("TELEGRAM"),
        TELEGRAM_BOT("TELEGRAM_BOT"),
        SMS("SMS"),
        SMTP("SMTP"),
        WHATSAPP("WHATSAPP"),
        PUSH("PUSH"),
        WEB("WEB"),
        VOICE("VOICE"),;
        private final String channelType;

        ChannelType(String channelType) {
            this.channelType = channelType;
        }

        public String getChannelType() {
            return this.channelType;
        }
    }

        public enum TelegramChannelType {
        TELEGRAM("TELEGRAM"),
        TELEGRAM_BOT("TELEGRAM_BOT"),;
        private final String telegramChannelType;

        TelegramChannelType(String telegramChannelType) {
            this.telegramChannelType = telegramChannelType;
        }

        public String getTelegramChannelType() {
            return this.telegramChannelType;
        }
    }


    public enum MessageFormat {
        TEXT("TEXT"),
        VERIFICATION("VERIFICATION"),
        PICTURE("PICTURE"),
        VOICE("VOICE"),
        OTP("OTP"),;
        private final String messageFormat;

        MessageFormat(String messageFormat) {
            this.messageFormat = messageFormat;
        }

        public String getMessageFormat() {
            return this.messageFormat;
        }
    }


    public enum MessageStatus {
        IN_PROGRESS("IN_PROGRESS"),
        SENT("SENT"),
        FAIL("FAIL"),;
        private final String messageStatus;

        MessageStatus(String messageStatus) {
            this.messageStatus = messageStatus;
        }

        public String getMessageStatus() {
            return this.messageStatus;
        }
    }

    public enum Priority {
        P1(1),
        P2(2),
        P3(3),
        P4(4),
        P5(5),
        P6(6),
        P7(7),;
        private final Integer priority;

        Priority(Integer priority) {
            this.priority = priority;
        }

        public Integer getPriority() {
            return this.priority;
        }
    }

    public enum OS {
        ANDROID("ANDROID"),
        IOS("IOS"),
        WEB("WEB"),;
        private final String osName;

        OS(String os) {
            this.osName = os;
        }

        public String getOS() {
            return this.osName;
        }
    }
}
