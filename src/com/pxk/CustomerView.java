package com.pxk;

/**
 * @author pxk
 * @create 2022-10-24-15:14
 */
public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public void enterMainMenu() {
        boolean isFlag = true;
        do {
            System.out.println("\n-----------------客户信息管理软件-----------------\n");
            System.out.println("                   1 添 加 客 户");
            System.out.println("                   2 修 改 客 户");
            System.out.println("                   3 删 除 客 户");
            System.out.println("                   4 客 户 列 表");
            System.out.println("                   5 退       出\n");
            System.out.print("                   请选择(1-5)：");

            char menu = CMUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.println("确认是否退出（Y/N）：");
                    char exit = CMUtility.readConfirmSelection();
                    if (exit == 'Y') {
                        isFlag = false;
                    }
                    break;
            }
        } while (isFlag);
    }

    private void addNewCustomer() {
        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(5);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(15);
        System.out.print("邮箱：");
        String email = CMUtility.readString(20);

        Customer customer = new Customer(name, gender, age, phone, email);
        boolean addFlag = customerList.addCustomer(customer);
        if (addFlag) {
            System.out.println("---------------------添加完成---------------------");
        } else {
            System.out.println("人数已达上限，添加失败！");
        }
    }

    private void modifyCustomer() {
        System.out.println("---------------------修改客户---------------------");
        Customer customer;
        int index;
        for (; ; ) {
            System.out.print("请选择待修改客户编号(-1退出)：");
            index = CMUtility.readInt();
            if (index == -1) {
                return;
            }
            customer = customerList.getCustomer(index - 1);
            if (customer == null) {
                System.out.println("无法找到指定客户！");
            } else {
                break;
            }
        }
        System.out.print("姓名（" + customer.getName() + "）：");
        String name = CMUtility.readString(5, customer.getName());

        System.out.print("性别（" + customer.getGender() + "）：");
        char gender = CMUtility.readChar(customer.getGender());

        System.out.print("年龄（" + customer.getAge() + "）：");
        int age = CMUtility.readInt(customer.getAge());

        System.out.print("电话（" + customer.getPhone() + "）：");
        String phone = CMUtility.readString(13, customer.getPhone());

        System.out.print("邮箱（" + customer.getEmail() + "）：");
        String email = CMUtility.readString(15, customer.getEmail());

        customer = new Customer(name, gender, age, phone, email);

        boolean modifyFlag = customerList.replaceCustomer(index - 1, customer);
        if (modifyFlag) {
            System.out.println("---------------------修改完成---------------------");
        } else {
            System.out.println("---------------------修改失败---------------------");
        }
    }

    private void deleteCustomer() {
        System.out.println("---------------------删除客户---------------------");
        Customer customer;
        int index;
        for (; ; ) {
            System.out.println("请选择待删除客户编号（-1退出）");
            index = CMUtility.readInt();
            if (index == -1) {
                return;
            }
            customer = customerList.getCustomer(index);
            if (customer == null) {
                System.out.println("无法找到指定客户！");
            } else {
                break;
            }
        }
        System.out.println("确认是否删除（Y/N）：");
        char deleteOrNot = CMUtility.readConfirmSelection();
        if (deleteOrNot == 'Y') {
            boolean deleteFlag = customerList.deleteCustomer(index);
            if (deleteFlag){
                System.out.println("---------------------删除完成---------------------");
            } else {
                System.out.println("---------------------删除失败---------------------");
            }
        }else {
            return;
        }
    }

    private void listAllCustomers() {
        System.out.println("---------------------------客户列表---------------------------");
        Customer[] customers = customerList.getAllCustomers();
        if (customers.length == 0) {
            System.out.println("没有任何客户记录！");
        } else {
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
            for (int i = 0; i < customers.length; i++) {
                Customer cust = customers[i];
                System.out.println((i + 1) + "\t" + cust.info());
            }
        }
        System.out.println("-------------------------客户列表完成-------------------------");
    }

    public static void main(String[] args) {
        CustomerView customerView = new CustomerView();
        customerView.enterMainMenu();
    }

}
