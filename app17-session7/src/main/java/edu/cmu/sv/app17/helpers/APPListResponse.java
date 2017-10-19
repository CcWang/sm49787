package edu.cmu.sv.app17.helpers;

public class APPListResponse {
    public boolean success;
    public Object content;
    public APPListResponseMetaData metadata;
    public APPListResponse(Object content, long total, long offset, long count) {
        this.success = true;
        this.content = content;
        this.metadata = new APPListResponseMetaData(total,offset,count);
    }

    public APPListResponse() {
        this.success = true;
    }

    private class APPListResponseMetaData {
        public long total,offset,count;
        public APPListResponseMetaData(long total, long offset, long count) {
            this.total = total;
            this.offset = offset;
            this.count = count;
        }
    }
}
