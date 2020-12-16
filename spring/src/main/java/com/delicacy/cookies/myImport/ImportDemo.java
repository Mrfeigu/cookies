package com.delicacy.cookies.myImport;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

/**
 * @author linzhenghui
 * @date 2020/12/16
 */
@Service
@Import(ColaImportService.class)
public class ImportDemo {

    private String importDemoName;

}
