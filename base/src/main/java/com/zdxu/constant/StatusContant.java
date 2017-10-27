package com.zdxu.constant;

/**
 * Created by zhaodexu on 2017/9/3.
 */
public interface StatusContant {

    public static interface OrderGoodsStatus {
        public static final int PENDING_SEND = 0;
        public static final int SENDING = 1;
        public static final int COMPLETE = 2;
    }

    public static interface OrderStatus {
        public static final int PENDING_ORDER = 0;
        public static final int ORDERED = 1;
        public static final int COMPLETE = 2;
    }
}
