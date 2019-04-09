package myself.annotationprocessor;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;


@AutoService(Processor.class)

@SupportedSourceVersion(SourceVersion.RELEASE_7)

@SupportedAnnotationTypes({"myself.annotationprocessor.testannotation"})
public class Processor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // set of track
        Map<String, String> trackMap = new HashMap<>();
        // print on gradle console
        Messager messager = processingEnv.getMessager();

        // 遍历annotations获取annotation类型 @SupportedAnnotationTypes
        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(te)) { // 获取所有被annotation标注的元素
                // 打印
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.toString());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.getSimpleName());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.getEnclosingElement().toString());
                // 获取注解
                testannotation annotation = e.getAnnotation(testannotation.class);
                // 获取名称
                String name = "".equals(annotation.name()) ? e.getSimpleName().toString() : annotation.name();
                // 保存映射信息
                trackMap.put(e.getSimpleName().toString(), e.toString());
                messager.printMessage(Diagnostic.Kind.NOTE, "映射关系：" + e.getSimpleName().toString() + "-" + name);
            }
        }

        try {
            // 生成的包名
            String genaratePackageName = "com.xud";
            // 生成的类名
            String genarateClassName = "TrackManager$Helper";

            // 创建Java文件
            JavaFileObject f = processingEnv.getFiler().createSourceFile(genarateClassName);
            messager.printMessage(Diagnostic.Kind.NOTE, "Printing: delete");
            f.delete();
            // 在控制台输出文件路径
            messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + f.toUri());
            Writer w = f.openWriter();
            try {
                PrintWriter pw = new PrintWriter(w);
                pw.println("package " + genaratePackageName + ";\n");
                pw.println("import java.util.Map;");
                pw.println("import myself.annotationprocessor.IData;\n");
                pw.println("/**");
                pw.println("* this file is auto-create by compiler,please don`t edit it");
                pw.println("* 页面路径映射关系表");
                pw.println("*/");
                pw.println("public class " + genarateClassName + " implements IData {");

                pw.println("\n    @Override");
                pw.println("    public void loadInto(Map<String, String> map) {");
                Iterator<String> keys = trackMap.keySet().iterator();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = trackMap.get(key);
                    pw.println("        map.put(\"" + key + "\",\"" + value + "\");");
                }
                pw.println("    }");
                pw.println("}");
                pw.flush();
            } finally {
                w.close();
            }
        } catch (IOException x) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, x.toString());
        }
        return false;
    }
}
