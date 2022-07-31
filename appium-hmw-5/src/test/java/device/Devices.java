package device;

import lombok.Data;

public enum Devices {

    ANDROID_OREO("src/test/resources/capabilities/android-oreo.json"),

    ANDROID_KITKAT("src/test/resources/capabilities/android-kitkat.json");

    public String path;

    Devices(String path) {
        this.path = path;
    }
}
