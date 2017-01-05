package bean_benchmarks;

public class JavaEditBean implements java.io.Serializable {
    private Long timestamp;
    private String channel;
    private String title;
    private String diffUrl;
    private String user;
    private Long byteDiff;
    private String summary;
    private Boolean isMinor;
    private Boolean isNew;
    private Boolean isUnpatrolled;
    private Boolean isBotEdit;
    private Boolean isSpecial;
    private Boolean isTalk;

    public JavaEditBean() {
        super();
    }

    public JavaEditBean(Long timestamp, String channel, String title, String diffUrl, String user,
                        Long byteDiff, String summary, Boolean isMinor, Boolean isNew, Boolean isUnpatrolled,
                        Boolean isBotEdit, Boolean isSpecial, Boolean isTalk) {
        super();
        this.timestamp = timestamp;
        this.channel = channel;
        this.title = title;
        this.diffUrl = diffUrl;
        this.user = user;
        this.byteDiff = byteDiff;
        this.summary = summary;
        this.isMinor = isMinor;
        this.isNew = isNew;
        this.isUnpatrolled = isUnpatrolled;
        this.isBotEdit = isBotEdit;
        this.isSpecial = isSpecial;
        this.isTalk = isTalk;
    }

    public Long getTimestamp() { return this.timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    public String getChannel() { return this.channel; }
    public void setChannel(String channel) { this.channel = channel; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getDiffurl() { return this.diffUrl; }
    public void setDiffurl(String diffUrl) { this.diffUrl = diffUrl; }
    public String getUser() { return this.user; }
    public void setUser(String user) { this.user = user; }
    public Long getByteDiff() { return this.byteDiff; }
    public void setByteDiff(Long byteDiff) { this.byteDiff = byteDiff; }
    public String getSummary() { return this.summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public Boolean getIsMinor() { return this.isMinor; }
    public void setIsMinor(Boolean isMinor) { this.isMinor = isMinor; }
    public boolean getIsnew() { return this.isNew; }
    public void setIsnew(Boolean isNew) { this.isNew = isNew; }
    public Boolean getIsUnpatrolled() { return this.isUnpatrolled; }
    public void setIsUnpatrolled(Boolean isUnpatrolled) { this.isUnpatrolled = isUnpatrolled; }
    public Boolean getIsBotEdit() { return this.isBotEdit; }
    public void setIsBotEdit(Boolean isBotEdit) { this.isBotEdit = isBotEdit; }
    public Boolean getIsSpecial() { return this.isSpecial; }
    public void setIsSpecial(Boolean isSpecial) { this.isSpecial = isSpecial; }
    public Boolean getIsTalk() { return this.isTalk; }
    public void setIsTalk(Boolean isTalk) { this.isTalk = isTalk; }
}
