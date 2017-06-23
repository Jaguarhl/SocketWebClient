package ru.kartsev.dmitry.socketwebclient.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestToServerDTO {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("msgid")
    @Expose
    private String msgid;
    @SerializedName("data")
    @Expose
    private Data data;

    public RequestToServerDTO(Data data, String generateMessageId, String type) {
        this.data = data;
        this.msgid = generateMessageId;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}