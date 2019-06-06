package manage.pojo;

/**
 * @author kuangjunlin
 */
public class Course implements Comparable<Course> {
    private String courseName;
    private double score;

    public static class Builder{
        private String courseName;
        private double score;

        public Builder(String courseName) {
            this.courseName = courseName;
        }

        public Builder(Course course) {
            this.courseName = course.courseName;
            this.score = course.score;
        }

        public Builder courseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Builder score(double score) {
            this.score = score;
            return this;
        }
        public Course build(){
            return new Course(this);
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public double getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }
    }

    public Course(Builder builder) {
        this.courseName = builder.getCourseName();
        this.score = builder.getScore();
    }

    public Course(String courseName, double score) {
        this.courseName = courseName;
        this.score = score;
    }

    @Override
    public int compareTo(Course o) {
        return (int)(o.getScore() - this.score);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", score=" + score +
                '}';
    }
}
