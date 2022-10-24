package com.pxk;

/**
 * @author pxk
 * @create 2022-10-24-14:19
 */
public class CustomerList {
    private Customer[] customers; //保存客户对象数据
    private int total;    //客户数量

    //初始化数组
    public CustomerList(int totalCustomer){
        customers = new Customer[totalCustomer];
    }

    //添加客户
    public boolean addCustomer(Customer customer){
        if (customer!=null&&total<customers.length){
            customers[total++] = customer;
            return true;
        }
        return false;
    }

    //替换客户
    public boolean replaceCustomer(int index,Customer customer){
        if (index>=0&&index<total){
            customers[index] = customer;
            return true;
        }
        return false;
    }

    //删除客户，后面的往前移，最后一个赋值为null
    public boolean deleteCustomer(int index){
        if (index>=0&&index<total){
            for (int i = index; i <total-1 ; i++) {
                customers[i] = customers[i+1];
            }
            customers[--total]=null;
            return true;
        }
        return false;
    }

    //返回客户数组
    // 在删除的方法中有给最后一个数据赋值为null的，
    // 因此不能直接返回customers数组，而是返回去掉后面null值后的数组
    public Customer[] getAllCustomers(){
        Customer[] custs = new Customer[total];
        for (int i = 0; i < custs.length; i++) {
            custs[i] = customers[i];
        }
        return custs;
    }

    //返回指定位置的Customer
    public Customer getCustomer(int index){
        if (index>=0&&index<total){
            return customers[index];
        }
        return null;
    }

    //返回客户数量，不能直接返回customers数组的长度（可能有null值）
    public int getTotal(){
        return total;
    }

}
