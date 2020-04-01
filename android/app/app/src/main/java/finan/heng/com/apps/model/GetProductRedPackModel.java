package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/20 16:14
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GetProductRedPackModel implements Serializable {


    /**
     * id : 60
     * userId : 1603
     * title : 新手好礼 - 100元红包
     * type : 0
     * source : 0
     * bonuses : 100.000
     * prerequisite : 5000.00
     * serviceConditions : 5000.00
     * timeLimit : 70
     * receiveTime : {"nanos":"0","time":"1495254234000","minutes":"23","seconds":"54","hours":"12","month":"4","year":"117","timezoneOffset":"-480","day":"6","date":"20"}
     * status : 0
     */

    private List<BonusesByTypeBean> bonusesByType;

    public List<BonusesByTypeBean> getBonusesByType() {
        return bonusesByType;
    }

    public void setBonusesByType(List<BonusesByTypeBean> bonusesByType) {
        this.bonusesByType = bonusesByType;
    }

    public static class BonusesByTypeBean {
        private String          id;
        private String          userId;
        private String          title;
        private String          type;
        private String          source;
        private String          bonuses;
        private String          prerequisite;
        private String          serviceConditions;
        private String          timeLimit;
        private String          endTime;
        public boolean isSelect;
        /**
         * nanos : 0
         * time : 1495254234000
         * minutes : 23
         * seconds : 54
         * hours : 12
         * month : 4
         * year : 117
         * timezoneOffset : -480
         * day : 6
         * date : 20
         */

        private ReceiveTimeBean receiveTime;
        private String          status;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getBonuses() {
            return bonuses;
        }

        public void setBonuses(String bonuses) {
            this.bonuses = bonuses;
        }

        public String getPrerequisite() {
            return prerequisite;
        }

        public void setPrerequisite(String prerequisite) {
            this.prerequisite = prerequisite;
        }

        public String getServiceConditions() {
            return serviceConditions;
        }

        public void setServiceConditions(String serviceConditions) {
            this.serviceConditions = serviceConditions;
        }

        public String getTimeLimit() {
            return timeLimit;
        }

        public void setTimeLimit(String timeLimit) {
            this.timeLimit = timeLimit;
        }

        public ReceiveTimeBean getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(ReceiveTimeBean receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class ReceiveTimeBean {
            private String nanos;
            private String time;
            private String minutes;
            private String seconds;
            private String hours;
            private String month;
            private String year;
            private String timezoneOffset;
            private String day;
            private String date;

            public String getNanos() {
                return nanos;
            }

            public void setNanos(String nanos) {
                this.nanos = nanos;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getMinutes() {
                return minutes;
            }

            public void setMinutes(String minutes) {
                this.minutes = minutes;
            }

            public String getSeconds() {
                return seconds;
            }

            public void setSeconds(String seconds) {
                this.seconds = seconds;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(String timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
