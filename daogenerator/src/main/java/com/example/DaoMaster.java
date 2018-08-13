package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class DaoMaster {
    public static void main(String[] args) {

        Schema schema = new Schema(1, "com.ganhuo.entity");
        addGanhuo(schema);
        schema.setDefaultJavaPackageDao("com.ganhuo.dao");
        try {
            new DaoGenerator().generateAll(schema,"C:\\Users\\123\\AndroidStudioProjects\\EasyApplication\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addGanhuo(Schema schema) {
        Entity entity = schema.addEntity("Ganhuo");
        entity.addIdProperty();//主键 Long类型
        entity.addStringProperty("createdAt");
        entity.addStringProperty("desc");
        entity.addStringProperty("publishedAt");
        entity.addStringProperty("source");
        Property p=entity.addStringProperty("type").getProperty();
        entity.addStringProperty("url");
        entity.addBooleanProperty("used");
        entity.addStringProperty("who");

//        Entity entity1=schema.addEntity("huazai");
//
//        entity.addToOne(entity1,p);
//        entity1.addStringProperty("type");
//        entity1.addIdProperty();

    }

    private static void addWarningBean(Schema schema) {
        //一对一关系
        Entity warningBean = schema.addEntity("WarningBean");
        warningBean.addStringProperty("pushTime");
        warningBean.addStringProperty("content");
        warningBean.addStringProperty("policeNo");

        Entity warningInfo = schema.addEntity("WarningInfo");
        warningInfo.addStringProperty("warningNo");
        warningInfo.addStringProperty("warningTime");
        warningInfo.addStringProperty("basePhoto");
        warningInfo.addStringProperty("capturePhoto");
        warningInfo.addStringProperty("captureScenePhoto");
        warningInfo.addStringProperty("name");
        warningInfo.addStringProperty("baseName");
        warningInfo.addStringProperty("score");
        warningInfo.addStringProperty("remark");
        warningInfo.addStringProperty("captureSuccess");
        warningInfo.addStringProperty("creareTime");

        Entity baseInfo = schema.addEntity("BaseInfo");
        baseInfo.addStringProperty("id").primaryKey();
        baseInfo.addStringProperty("name");
        baseInfo.addStringProperty("nation");
        baseInfo.addStringProperty("gender");
        baseInfo.addStringProperty("nativePlace");
        baseInfo.addStringProperty("credentialsNo");
        baseInfo.addStringProperty("address");
        baseInfo.addStringProperty("baseLib");
        baseInfo.addStringProperty("baseCase");
        baseInfo.addStringProperty("remark");
        baseInfo.addStringProperty("baseImage");

        Property id=warningBean.addStringProperty("id").primaryKey().getProperty();
        warningBean.addToOne(warningInfo,id);

        Property warningId=warningInfo.addStringProperty("warningId").primaryKey().getProperty();
        warningInfo.addToOne(baseInfo,warningId);


    }
}
