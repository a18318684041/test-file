package com.example.administrator.test_file;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class person {

    /**
     * result_num : 1
     * result : [{"location":{"left":294,"top":854,"width":492,"height":426},"face_probability":0.98424702882767,"rotation_angle":-17,"yaw":5.7203598022461,"pitch":2.0150647163391,"roll":-17.004875183105,"expression":0,"expression_probablity":0.99999523162842}]
     * log_id : 1259710445
     */

    private int result_num;
    private int log_id;
    /**
     * location : {"left":294,"top":854,"width":492,"height":426}
     * face_probability : 0.98424702882767
     * rotation_angle : -17
     * yaw : 5.7203598022461
     * pitch : 2.0150647163391
     * roll : -17.004875183105
     * expression : 0
     * expression_probablity : 0.99999523162842
     */

    private List<ResultBean> result;

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * left : 294
         * top : 854
         * width : 492
         * height : 426
         */

        private LocationBean location;
        private double face_probability;
        private int rotation_angle;
        private double yaw;
        private double pitch;
        private double roll;
        private int expression;
        private double expression_probablity;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public double getFace_probability() {
            return face_probability;
        }

        public void setFace_probability(double face_probability) {
            this.face_probability = face_probability;
        }

        public int getRotation_angle() {
            return rotation_angle;
        }

        public void setRotation_angle(int rotation_angle) {
            this.rotation_angle = rotation_angle;
        }

        public double getYaw() {
            return yaw;
        }

        public void setYaw(double yaw) {
            this.yaw = yaw;
        }

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getRoll() {
            return roll;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }

        public int getExpression() {
            return expression;
        }

        public void setExpression(int expression) {
            this.expression = expression;
        }

        public double getExpression_probablity() {
            return expression_probablity;
        }

        public void setExpression_probablity(double expression_probablity) {
            this.expression_probablity = expression_probablity;
        }

        public static class LocationBean {
            private int left;
            private int top;
            private int width;
            private int height;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
