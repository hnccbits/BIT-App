package com.hnccbits.bit_portal;

class Data {
        private  String mDataText;
        private int mDataImgRecourceId;

        public Data(String text,int resourceId)
        {
            mDataText=text;
            mDataImgRecourceId=resourceId;
        }
        public String getDataText()
        {
            return mDataText;
        }
        public int getmDataImgRecourceId()
        {
            return mDataImgRecourceId;
        }

}
