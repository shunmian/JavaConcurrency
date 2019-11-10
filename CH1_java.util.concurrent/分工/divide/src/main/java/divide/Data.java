package divide;

import java.io.FileWriter;
import java.io.IOException;

public class Data {
  private String fileName;
  private String content;
  private Boolean changed;

  Data(String fileName, String content) {
    this.fileName = fileName;
    this.content = content;
    this.changed = false;
  }

  synchronized void change(String content) {
    this.content = content;
    this.changed = true;
  }

  synchronized void save() throws IOException {
    if (!this.changed) {
      return;
    }
    this.doSave();
  }

  void doSave() throws IOException {
    System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
    FileWriter fileWrite = new FileWriter(this.fileName);
    fileWrite.write(this.content);
    fileWrite.close();
    this.changed = false;
  }
  
}