package cn.gdgst.shuoke.tablet.api;

/**
 * Created by Administrator on 2/7 0007.
 */

public class ApiConstants {

    public static final String SHUO_KE_HOST = "http://shuoke360.cn";
    public static final String NETEAST_HOST = "http://shuoke360.cn/api/";
    public static final String BEI_YONG_01_HOST = "";
    public static final String BEI_YONG_02_HOST = "";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.SHUO_KE:
                host = NETEAST_HOST;
                break;
            case HostType.BEI_YONG_01:
                host = BEI_YONG_01_HOST;
                break;
            case HostType.BEI_YONG_02:
                host = BEI_YONG_02_HOST;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

}
