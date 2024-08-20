package POJO;

public class GetCourse {

    private String url;
    private String services;
    private String expertise;
    private Courses Courses;
    private String instructor;
    private String linkedIn;

    //Create getter and setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public POJO.Courses getCourses() {
        //Courses class is the return type of getCoursses getter and setter
        return Courses;
    }

    public void setCourses(POJO.Courses courses) {

        Courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }



    //Create getter setter in eclipese -> alt+shft+s
    //Create getter and stter in IntelliJ -> alt+insert
}


