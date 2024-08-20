package POJO;

import java.util.List;

public class Courses {

    private List<webAutomation> webAutomation; //webAutomation class object invoked
    private List<API> api; //API class object invoked
    private List<Mobile> mobile; //Mobile class object invoked

    public List<POJO.webAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<POJO.webAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<API> getApi() {
        return api;
    }

    public void setApi(List<API> api) {
        this.api = api;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }

}
