package com.mdd.admin.service.channel;

import java.util.Map;

public interface IChannelOaReplyService {

    Object list();

    Object detail();

    void add(Map<String, String> params);

    void edit();

    void del();

}
