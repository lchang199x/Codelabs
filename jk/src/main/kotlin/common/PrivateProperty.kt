package common

class PrivateProperty1(val field1: Int, val field2: Int) {
    val a = listOf<Int>().isNullOrEmpty()
}
/*
// WILL DECOMPILE TO:
public final class PrivateProperty1 {
   private final int field1;
   private final int field2;

   public final int getField1() {
      return this.field1;
   }

   public final int getField2() {
      return this.field2;
   }

   public PrivateProperty1(int field1, int field2) {
      this.field1 = field1;
      this.field2 = field2;
   }
}
*/

class PrivateProperty2(val field1: Int, private val field2: Int)
/*
// WILL DECOMPILE TO:
public final class PrivateProperty2 {
   private final int field1;
   private final int field2;

   public final int getField1() {
      return this.field1;
   }

   public PrivateProperty2(int field1, int field2) {
      this.field1 = field1;
      this.field2 = field2;
   }
}
*/

class PrivateProperty3(var field1: Int, private var field2: Int)
/*
// WILL DECOMPILE TO:
public final class PrivateProperty3 {
   private int field1;
   private int field2;

   public final int getField1() {
      return this.field1;
   }

   public final void setField1(int var1) {
      this.field1 = var1;
   }

   public PrivateProperty3(int field1, int field2) {
      this.field1 = field1;
      this.field2 = field2;
   }
}
*/