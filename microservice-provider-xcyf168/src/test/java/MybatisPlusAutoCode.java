import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis-plus 自动生成代码
 * @problemor wolf
 * @TableField(exist = false) 排除注释
 */
public class MybatisPlusAutoCode {

    //生成注释作者名称
    private static String problemOR = "wolf";
    //mapper包名
    private static String MAPPER_PACKAGE = "com.zhenai.emotion.user.provider.dao.mapper";
    //XML包名
    private static String XML_PACKAGE = "mapper.user";
    //实体包名
    private static String MODEL_PACKAGE = "com.xcyf.springcloud.entity";
    //接口包名
    private static String SERVICE_PACKAGE = "com.xcyf.springcloud.service";
    //接口实现类包名
    private static String SERVICEIMPL_PACKAGE = "com.xcyf.springcloud.mapper";
    //工程本地路径根目录
    private static String LOCAL_PROJECT_ROOT_PATH = "E:\\myCode\\";
    //数据库地址
    private static String DB_URI = "jdbc:mysql://10.51.7.105:3306/zhenai_emotion_offline?characterEncoding=utf8";
    //数据库账号
    private static String DB_USERNAME = "u_96333";
    //数据库密码
    private static String DB_PASSWORD = "u_96333";
    //表名
    private static String[] DB_TABLE_NAMES = new String[]{"XcyfProduct"};

    public static String[][] models = new String[][] {
            { "mapper", LOCAL_PROJECT_ROOT_PATH + "/microservice-provider-xcyf168/src/main/java"},
            { "xml", LOCAL_PROJECT_ROOT_PATH + "/microservice-provider-xcyf168/src/main/resources"},
            { "model", LOCAL_PROJECT_ROOT_PATH + "/microservice-provider-xcyf168/src/main/java"}/*,
		{ "facade", LOCAL_PROJECT_ROOT_PATH + "/zhenai-mobile-interface/src/main/java"},
		{ "serviceImpl", LOCAL_PROJECT_ROOT_PATH + "/zhenai-mobile-provider/src/main/java"}*/
    };

    public static void main(String[] args) {
        for (String[] model : models) {
            shell(model);
        }
    }

    private static void shell(String[] model) {
        String path = model[1];
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path);
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor(problemOR);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
//		dsc.setTypeConvert(new MySqlTypeConvert() {
//			// 自定义数据库表字段类型转换【可选】
//			@Override
//			public DbColumnType processTypeConvert(String fieldType) {
//				System.out.println("转换类型：" + fieldType);
//				// 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
//				return super.processTypeConvert(fieldType);
//			}
//		});
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(DB_USERNAME);
        dsc.setPassword(DB_PASSWORD);
        dsc.setUrl(DB_URI);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });//
        // 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setEntityColumnConstant(true);
        strategy.setInclude(DB_TABLE_NAMES); // 需要生成的表
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("");
        pc.setEntity(MODEL_PACKAGE);
        pc.setMapper(MAPPER_PACKAGE);
        pc.setXml(XML_PACKAGE);
        pc.setService(SERVICE_PACKAGE);
        pc.setServiceImpl(SERVICEIMPL_PACKAGE);
        mpg.setPackageInfo(pc);



        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        if ("mapper".equals(model[0])) {
            tc.setController(null);
            tc.setEntity(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setXml(null);
        } else if ("model".equals(model[0])) {
            tc.setController(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setMapper(null);
            tc.setXml(null);
        } else if ("xml".equals(model[0])) {
            tc.setController(null);
            tc.setMapper(null);
            tc.setEntity(null);
            tc.setService(null);
            tc.setServiceImpl(null);
        } else if ("facade".equals(model[0])) {
            tc.setController(null);
            tc.setMapper(null);
            tc.setEntity(null);
            tc.setXml(null);
            tc.setServiceImpl(null);
        } else if ("serviceImpl".equals(model[0])) {
            tc.setController(null);
            tc.setMapper(null);
            tc.setEntity(null);
            tc.setXml(null);
            tc.setService(null);
        }
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }

}
