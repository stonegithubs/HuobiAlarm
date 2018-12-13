package com.tdx.fastlib.okhttp.builder;


import com.tdx.fastlib.okhttp.OkHttpUtils;
import com.tdx.fastlib.okhttp.request.OtherRequest;
import com.tdx.fastlib.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
