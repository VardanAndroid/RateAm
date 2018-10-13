package am.rate.com.rateamtest.view.model;

public class Branch {

    private final String mId;
    private Title mTitle;
    private Address mAddress;
    private boolean isHead;
    private String mContact;
    private String mWorkingHours;

    public Branch(String id) {
        mId = id;
    }

    public Title getTitle() {
        return mTitle;
    }

    public void setTitle(Title title) {
        mTitle = title;
    }

    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(Address address) {
        mAddress = address;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        mContact = contact;
    }

    public String getWorkingHours() {
        return mWorkingHours;
    }

    public void setWorkingHours(String workingHours) {
        mWorkingHours = workingHours;
    }

    public String getId() {
        return mId;
    }
}
