package department.config;

public class JavaConfiguration implements Configuration{
    @Override
    public String getPackageToScan() {
        return "department";
    }
}
