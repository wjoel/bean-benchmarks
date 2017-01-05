package bean_benchmarks;

public class PrimitiveJavaEditBean implements java.io.Serializable {
    private long timestamp;
    private String channel;
    private String title;
    private String diffUrl;
    private String user;
    private long byteDiff;
    private String summary;
    private boolean isMinor;
    private boolean isNew;
    private boolean isUnpatrolled;
    private boolean isBotEdit;
    private boolean isSpecial;
    private boolean isTalk;

    public PrimitiveJavaEditBean() {
        super();
    }

    public PrimitiveJavaEditBean(long timestamp, String channel, String title, String diffUrl, String user,
                                 long byteDiff, String summary, boolean isMinor, boolean isNew, boolean isUnpatrolled,
                                 boolean isBotEdit, boolean isSpecial, boolean isTalk) {
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

    public long getTimestamp() { return this.timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public String getChannel() { return this.channel; }
    public void setChannel(String channel) { this.channel = channel; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getDiffurl() { return this.diffUrl; }
    public void setDiffurl(String diffUrl) { this.diffUrl = diffUrl; }
    public String getUser() { return this.user; }
    public void setUser(String user) { this.user = user; }
    public long getByteDiff() { return this.byteDiff; }
    public void setByteDiff(long byteDiff) { this.byteDiff = byteDiff; }
    public String getSummary() { return this.summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public boolean getIsMinor() { return this.isMinor; }
    public void setIsMinor(boolean isMinor) { this.isMinor = isMinor; }
    public boolean getIsnew() { return this.isNew; }
    public void setIsnew(boolean isNew) { this.isNew = isNew; }
    public boolean getIsUnpatrolled() { return this.isUnpatrolled; }
    public void setIsUnpatrolled(boolean isUnpatrolled) { this.isUnpatrolled = isUnpatrolled; }
    public boolean getIsBotEdit() { return this.isBotEdit; }
    public void setIsBotEdit(boolean isBotEdit) { this.isBotEdit = isBotEdit; }
    public boolean getIsSpecial() { return this.isSpecial; }
    public void setIsSpecial(boolean isSpecial) { this.isSpecial = isSpecial; }
    public boolean getIsTalk() { return this.isTalk; }
    public void setIsTalk(boolean isTalk) { this.isTalk = isTalk; }
}
