# CG

✅ Problem Statement 1 – Procedure & Trigger
🔹 Step 1: Create Table
CREATE TABLE empp(
   eid NUMBER,
   ename VARCHAR2(20),
   salary NUMBER,
   dept VARCHAR2(20)
);
🔹 Step 2: Create Procedure
CREATE OR REPLACE PROCEDURE add_employee(
   p_eid NUMBER,
   p_ename VARCHAR2,
   p_salary NUMBER,
   p_dept VARCHAR2
)
IS
BEGIN
   INSERT INTO empp
   VALUES(p_eid,p_ename,p_salary,p_dept);

   DBMS_OUTPUT.PUT_LINE('Employee Inserted');
END;
/
🔹 Step 3: Create Trigger
CREATE OR REPLACE TRIGGER trg_uppercase
BEFORE INSERT
ON empp
FOR EACH ROW
BEGIN
   :NEW.ename := UPPER(:NEW.ename);
END;
/
🔹 Step 4: Execute Procedure
BEGIN
   add_employee(1,'teju',50000,'IT');
   add_employee(2,'ram',40000,'HR');
END;
/
🔹 Step 5: Display Records
SELECT * FROM empp;
✅ Problem Statement 2 – Procedure & Function
🔹 Procedure: emp_by_dept()
CREATE OR REPLACE PROCEDURE emp_by_dept(
   p_dept VARCHAR2
)
IS
BEGIN
   FOR rec IN (
      SELECT * FROM empp
      WHERE dept = p_dept
   )
   LOOP
      DBMS_OUTPUT.PUT_LINE(
         rec.eid || ' ' ||
         rec.ename || ' ' ||
         rec.salary
      );
   END LOOP;
END;
/
🔹 Function: get_salary()
CREATE OR REPLACE FUNCTION get_salary(
   p_eid NUMBER
)
RETURN NUMBER
IS
   v_sal NUMBER;
BEGIN
   SELECT salary
   INTO v_sal
   FROM empp
   WHERE eid = p_eid;

   RETURN v_sal;
END;
/
🔹 Execute Procedure
BEGIN
   emp_by_dept('IT');
END;
/
🔹 Execute Function
DECLARE
   v_salary NUMBER;
BEGIN
   v_salary := get_salary(1);

   DBMS_OUTPUT.PUT_LINE(
      'Salary = ' || v_salary
   );
END;
/
✅ Problem Statement 3 – Procedure & Trigger
🔹 Step 1: Create Table
CREATE TABLE student(
   sid NUMBER,
   sname VARCHAR2(20),
   marks NUMBER,
   grade VARCHAR2(5)
);
🔹 Step 2: Create Procedure
CREATE OR REPLACE PROCEDURE add_student(
   p_sid NUMBER,
   p_sname VARCHAR2,
   p_marks NUMBER
)
IS
BEGIN
   INSERT INTO student(sid,sname,marks)
   VALUES(p_sid,p_sname,p_marks);

   DBMS_OUTPUT.PUT_LINE('Student Inserted');
END;
/
🔹 Step 3: Create Trigger
CREATE OR REPLACE TRIGGER trg_grade
BEFORE INSERT
ON student
FOR EACH ROW
BEGIN
   IF :NEW.marks >= 80 THEN
      :NEW.grade := 'A';

   ELSIF :NEW.marks >= 60 THEN
      :NEW.grade := 'B';

   ELSE
      :NEW.grade := 'C';
   END IF;
END;
/
🔹 Step 4: Execute Procedure
BEGIN
   add_student(1,'Teju',85);
   add_student(2,'Ram',65);
END;
/
🔹 Step 5: Display Records
SELECT * FROM student;
✅ Problem Statement 4 – Function & Cursor
🔹 Step 1: Create Table
CREATE TABLE product(
   pid NUMBER,
   pname VARCHAR2(20),
   price NUMBER,
   quantity NUMBER
);
🔹 Insert Records
INSERT INTO product VALUES(1,'Pen',10,50);
INSERT INTO product VALUES(2,'Book',100,20);
COMMIT;
🔹 Step 2: Create Function
CREATE OR REPLACE FUNCTION total_amount
RETURN NUMBER
IS
   total NUMBER;
BEGIN
   SELECT SUM(price * quantity)
   INTO total
   FROM product;

   RETURN total;
END;
/
🔹 Step 3: Cursor Program
DECLARE
   CURSOR c1 IS
      SELECT * FROM product;

   rec product%ROWTYPE;
BEGIN
   OPEN c1;

   LOOP
      FETCH c1 INTO rec;

      EXIT WHEN c1%NOTFOUND;

      DBMS_OUTPUT.PUT_LINE(
         rec.pid || ' ' ||
         rec.pname || ' ' ||
         rec.price || ' ' ||
         rec.quantity
      );
   END LOOP;

   CLOSE c1;
END;
/
🔹 Step 4: Execute Function
DECLARE
   v_total NUMBER;
BEGIN
   v_total := total_amount;

   DBMS_OUTPUT.PUT_LINE(
      'Total Amount = ' || v_total
   );
END;
/
