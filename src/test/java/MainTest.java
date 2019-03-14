import org.mybatis.generator.api.ShellRunner;

/**
 * Created by renhongqiang on 2019-03-14 15:15
 */
public class MainTest {
    public static void main(String[] args) {
        String config = MainTest.class.getClassLoader()
                .getResource("generatorConfig.xml").getFile();
        String[] parameters = { "-configfile", config, "-overwrite" };

        ShellRunner.main(parameters);
    }
}
