package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * 电脑组装工程师类
 * @author tgl
 * @data create in 8:57 2018/4/7
 */
public class ComputerEngineer {
    private CPU cpu;
    /**
     * 主板
     */
    private MainBoard mainBoard;

//    public void makeComputer(int cpuType, int mainBoardType) {
//        /**
//         * 组装机器的基本步骤
//         */
//        //1:首先准备好装机所需要的配件
//        prepareHardwares(cpuType, mainBoardType);
//        //2:组装机器
//        //3:测试机器
//        //4:交付客户
//    }

    public void makeComputer(AbstractFactory factory) {
        /**
         * 组装机器的基本步骤
         */
        //1:首先准备好装机所需要的配件
        prepareHardwares(factory);
        //2:组装机器
        //3:测试机器
        //4:交付客户
    }

//    private void prepareHardwares(int cpuType, int mainBoardType) {
//        this.cpu = CPUFactory.createCPU(cpuType);
//        // 测试硬件是否正常
//        this.cpu.calculate();
//
//        this.mainBoard = MainBoardFactory.createMainBoard(mainBoardType);
//        this.mainBoard.installCPU();
//    }

    private void prepareHardwares(AbstractFactory factory) {
        this.cpu = factory.createCPU();
        // 测试硬件是否正常
        this.cpu.calculate();

        this.mainBoard = factory.createMainBoard();
        this.mainBoard.installCPU();
    }

    public static void main(String[] args){
        ComputerEngineer computerEngineer = new ComputerEngineer();
//        computerEngineer.makeComputer(1, 2);
        AbstractFactory factory = new InterFactory();
        computerEngineer.makeComputer(factory);
    }
}
