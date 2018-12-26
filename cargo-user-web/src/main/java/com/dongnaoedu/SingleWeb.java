package com.dongnaoedu;

import com.dongnaoedu.aim.MakeSrcDoc;
import com.dongnaoedu.vo.PendingDocVo;
import com.dongnaoedu.aim.ProblemBank;
import com.dongnaoedu.service.DocService;

import java.util.List;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/12/04
 * 创建时间: 22:11
 */
public class SingleWeb {
    public static void main(String[] args) {

        System.out.println("题库开始初始化...........");
        ProblemBank.initBank();
        System.out.println("题库初始化完成。");

        List<PendingDocVo> docList = MakeSrcDoc.makeDoc(2);
        long startTotal = System.currentTimeMillis();
        for(PendingDocVo doc:docList){
            System.out.println("开始处理文档："+doc.getDocName()+".......");
            long start = System.currentTimeMillis();
            String localName = DocService.makeDoc(doc);
            System.out.println("文档"+localName+"生成耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            start = System.currentTimeMillis();
            String remoteUrl = DocService.upLoadDoc(localName);
            System.out.println("已上传至["+remoteUrl+"]耗时："
                    +(System.currentTimeMillis()-start)+"ms");
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }
}
