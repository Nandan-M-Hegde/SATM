
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Nandan on 06-11-2017.
 */
class ATM extends JFrame {

    private JPanel LPanel;
    private JPasswordField pw;
    private JPanel PWPanel;
    private int COp;
    private JPanel SoCPanel;
    private JPanel mainPanel;
    private JPanel depositPanel;
    private JPanel balancePanel;
    private JPanel makeNextPanel;

    private int currentBalance = 10000;
    private int savingsBalance = 10000;
    private int amount = 0;

    ATM() {
        super("ATM");
        dispMenu(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void dispMenu(JPanel P2R) {
        if (P2R != null) this.remove(P2R);

        JLabel l = new JLabel();
        l.setText("Welcome dear user");
        l.setForeground(Color.BLACK);

        JButton cont = new JButton("Continue");
        JButton cancel = new JButton("Cancel");

        ActionListener list = e -> {
            if (e.getActionCommand().equals("Cancel")) {
                showFinalPanel(LPanel);
                return;
            }
            showPinMenu(LPanel, "");
        };
        cont.addActionListener(list);

        LPanel = new JPanel();
        LPanel.setBackground(Color.LIGHT_GRAY);
        LPanel.setSize(new Dimension(150, 150));
        LPanel.setLayout(new GridBagLayout());

        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        LPanel.add(l, GBconstraints);

        setGBconstraints(GBconstraints, 0, 1);
        LPanel.add(cont, GBconstraints);

        cancel.addActionListener(list);
        setGBconstraints(GBconstraints, 2, 1);
        LPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;

        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(LPanel, GBconstraints);
        this.setVisible(true);
    }

    private void showPinMenu(JPanel P2R, String ErrorMsg) {
        if (P2R != null)
            this.remove(P2R);
        JLabel l1 = new JLabel("Enter your 4 digit PIN");
        JLabel l2 = new JLabel("(PIN is 1111)");
        pw = new JPasswordField(4);
        pw.setFocusable(true);
        pw.requestFocusInWindow();
        JButton OK = new JButton("Okay");
        final int[] count = {0};
        ActionListener list = e -> {
            String pin = new String(ATM.this.pw.getPassword());
            if (pin.equals("1111")) {
                showMainMenu(PWPanel);
                count[0]++;
            } else if (count[0] < 3)
                showPinMenu(PWPanel, "Error, You entered a wrong password!");
            else {
                showFinalPanel(LPanel);
            }
        };
        OK.addActionListener(list);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> dispMenu(PWPanel));

        PWPanel = new JPanel();
        PWPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLACK);
        PWPanel.setSize(new Dimension(150, 150));
        PWPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();
        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        if (!ErrorMsg.equals("")) {
            JLabel error = new JLabel("You entered a wrong PIN");
            error.setForeground(Color.RED);
            PWPanel.add(error, GBconstraints);
        }
        setGBconstraints(GBconstraints, 1, 1);
        PWPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 1, 2);
        PWPanel.add(l2, GBconstraints);
        setGBconstraints(GBconstraints, 1, 3);
        PWPanel.add(pw, GBconstraints);
        setGBconstraints(GBconstraints, 0, 4);
        PWPanel.add(OK, GBconstraints);
        setGBconstraints(GBconstraints, 2, 4);
        PWPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(PWPanel, GBconstraints);
        pw.setFocusable(true);
        pw.requestFocusInWindow();
        getRootPane().setDefaultButton(OK);
        this.setVisible(true);
    }

    private void showMainMenu(JPanel P2R) {
        if (P2R != null) this.remove(P2R);

        ActionListener list = e -> {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "Deposit":
                    SoC(mainPanel, 0);
                    break;
                case "Withdraw":
                    SoC(mainPanel, 1);
                    break;
                case "Get Balance":
                    SoC(mainPanel, 2);
                    break;
                case "Transfer":
                    SoC(mainPanel, 3);
                    break;
                case "Cancel":
                    showFinalPanel(mainPanel);
                    break;
            }
        };

        JLabel l1 = new JLabel("Select Transaction");
        JButton deposit = new JButton("Deposit");
        deposit.addActionListener(list);
        JButton withdraw = new JButton("Withdraw");
        withdraw.addActionListener(list);
        JButton balance = new JButton("Get Balance");
        balance.addActionListener(list);
        JButton transfer = new JButton("Transfer");
        transfer.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLACK);
        mainPanel.setSize(new Dimension(150, 150));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 0, 1);
        mainPanel.add(deposit, GBconstraints);
        setGBconstraints(GBconstraints, 2, 1);
        mainPanel.add(withdraw, GBconstraints);
        setGBconstraints(GBconstraints, 0, 2);
        mainPanel.add(balance, GBconstraints);
        setGBconstraints(GBconstraints, 2, 2);
        mainPanel.add(transfer, GBconstraints);
        setGBconstraints(GBconstraints, 1, 3);
        mainPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(mainPanel, GBconstraints);
        this.setVisible(true);
    }

    private void SoC(JPanel P2R, int op) {
        if (P2R != null)
            this.remove(P2R);

        COp = op;
        ActionListener list = e -> {
            String cmd = e.getActionCommand();
            int account = 0;
            switch (cmd) {
                case "Current":
                    account = 0;
                    break;
                case "Savings":
                    account = 1;
                    break;
                case "From Savings":
                    account = 0;
                    break;
                case "From Current":
                    account = 1;
                    break;
                case "Cancel":
                    showMainMenu(SoCPanel);
                    return;
            }
            switch (ATM.this.COp) {
                case 0:
                    showDeposit(SoCPanel, account);
                    break;
                case 1:
                    showWithdraw(SoCPanel, account);
                    break;
                case 2:
                    showBalance(SoCPanel, account);
                    break;
                case 3:
                    showTransfer(SoCPanel, account);
                    break;
            }
        };

        JLabel l1 = new JLabel("Select Account");
        JButton current = new JButton("Current");
        if (op == 3) {
            current.setText("From Current");
        }
        current.addActionListener(list);
        JButton savings = new JButton("Savings");
        if (op == 3) {
            savings.setText("From Savings");
        }
        savings.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        SoCPanel = new JPanel();
        SoCPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLACK);
        SoCPanel.setSize(new Dimension(150, 150));
        SoCPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        SoCPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 0, 2);
        SoCPanel.add(current, GBconstraints);
        setGBconstraints(GBconstraints, 2, 2);
        SoCPanel.add(savings, GBconstraints);
        setGBconstraints(GBconstraints, 1, 3);
        SoCPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(SoCPanel, GBconstraints);
        this.setVisible(true);
    }

    private void showTransfer(JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);
        ActionListener list = (ActionEvent e) -> {
            String cmd = e.getActionCommand();

            switch (cmd) {
                case "₹100":
                    amount = 100;
                    break;
                case "₹200":
                    amount = 200;
                    break;
                case "₹500":
                    amount = 500;
                    break;
                case "₹1000":
                    amount = 1000;
                    break;
                case "₹2000":
                    amount = 2000;
                    break;
                case "₹5000":
                    amount = 5000;
                    break;
                case "Cancel":
                    showMainMenu(depositPanel);
                    return;
            }
            if (account == 0) {
                setCurrentBalance(getCurrentBalance() - amount);
                setSavingsBalance(getSavingsBalance() + amount);
            } else if (account == 1) {
                setCurrentBalance(getCurrentBalance() + amount);
                setSavingsBalance(getSavingsBalance() - amount);
            }
            showMakeOtherTransaction(depositPanel);
        };

        JLabel l1 = new JLabel("Transfer");
        JLabel l2 = new JLabel("Select Amount");

        JButton amt1 = new JButton("₹100");
        amt1.addActionListener(list);
        JButton amt2 = new JButton("₹200");
        amt2.addActionListener(list);
        JButton amt3 = new JButton("₹500");
        amt3.addActionListener(list);
        JButton amt4 = new JButton("₹1000");
        amt4.addActionListener(list);
        JButton amt5 = new JButton("₹2000");
        amt5.addActionListener(list);
        JButton amt6 = new JButton("₹5000");
        amt6.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        JTextField amtField = new JTextField(10);
        depositPanel = new JPanel();
        depositPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLACK);
        l2.setForeground(Color.BLACK);
        depositPanel.setSize(new Dimension(150, 150));
        depositPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        depositPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 1, 1);
        depositPanel.add(l2, GBconstraints);
        setGBconstraints(GBconstraints, 0, 2);
        depositPanel.add(amt1, GBconstraints);
        setGBconstraints(GBconstraints, 0, 3);
        depositPanel.add(amt2, GBconstraints);
        setGBconstraints(GBconstraints, 0, 4);
        depositPanel.add(amt3, GBconstraints);
        setGBconstraints(GBconstraints, 1, 5);
        depositPanel.add(cancel, GBconstraints);
        setGBconstraints(GBconstraints, 1, 2);
        depositPanel.add(amtField, GBconstraints);
        setGBconstraints(GBconstraints, 2, 2);
        depositPanel.add(amt4, GBconstraints);
        setGBconstraints(GBconstraints, 2, 3);
        depositPanel.add(amt5, GBconstraints);
        setGBconstraints(GBconstraints, 2, 4);
        depositPanel.add(amt6, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(depositPanel, GBconstraints);
        amtField.setFocusable(true);
        amtField.requestFocusInWindow();
        this.setVisible(true);
    }

    private void showBalance(JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);
        JLabel label = new JLabel(("Account Balance ") + ((account == 0) ? "Current" : "Savings"));

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> showMakeOtherTransaction(balancePanel));
        JTextField amountFieldText = new JTextField(10);
        if (account == 0) {
            amountFieldText.setText("₹ " + getCurrentBalance() + " ");
        } else if (account == 1) {
            amountFieldText.setText("₹ " + getSavingsBalance() + " ");
        }
        amountFieldText.setEditable(false);

        balancePanel = new JPanel();
        balancePanel.setBackground(Color.LIGHT_GRAY);
        label.setForeground(Color.BLACK);
        balancePanel.setSize(new Dimension(150, 150));
        balancePanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        balancePanel.add(label, GBconstraints);
        setGBconstraints(GBconstraints, 1, 2);
        balancePanel.add(amountFieldText, GBconstraints);
        setGBconstraints(GBconstraints, 1, 3);
        balancePanel.add(ok, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(balancePanel, GBconstraints);
        this.setVisible(true);
    }

    private void showWithdraw(JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);

        ActionListener list = e -> {
            String command = e.getActionCommand();
            int amount = 0;
            switch (command) {
                case "₹100":
                    amount = 100;

                    break;
                case "₹200":
                    amount = 200;

                    break;
                case "₹500":
                    amount = 500;

                    break;
                case "₹1000":
                    amount = 1000;

                    break;
                case "₹2000":
                    amount = 2000;

                    break;
                case "₹5000":
                    amount = 5000;

                    break;
                case "Cancel":
                    showMainMenu(depositPanel);
                    return;
            }
            if (account == 0) {
                setCurrentBalance(getCurrentBalance() - amount);
                if (currentBalance < 0) {
                    currentBalance = 0;
                    InsufficientBalance(depositPanel);
                } else
                    showMakeOtherTransaction(depositPanel);
            } else if (account == 1) {
                setSavingsBalance(getSavingsBalance() - amount);
                if (savingsBalance < 0) {
                    savingsBalance = 0;
                    InsufficientBalance(depositPanel);
                } else
                    showMakeOtherTransaction(depositPanel);
            }
        };

        JLabel l1 = new JLabel("Withdraw");
        JLabel l2 = new JLabel("Select Amount");

        JButton amt1 = new JButton("₹100");
        amt1.addActionListener(list);
        JButton amt2 = new JButton("₹200");
        amt2.addActionListener(list);
        JButton amt3 = new JButton("₹500");
        amt3.addActionListener(list);
        JButton amt4 = new JButton("₹1000");
        amt4.addActionListener(list);
        JButton amt5 = new JButton("₹2000");
        amt5.addActionListener(list);
        JButton amt6 = new JButton("₹5000");
        amt6.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);
        JTextField amountField = new JTextField(10);

        depositPanel = new JPanel();
        depositPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLACK);
        l2.setForeground(Color.BLACK);
        depositPanel.setSize(new Dimension(150, 150));
        depositPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        depositPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 1, 1);
        depositPanel.add(l2, GBconstraints);
        setGBconstraints(GBconstraints, 0, 2);
        depositPanel.add(amt1, GBconstraints);
        setGBconstraints(GBconstraints, 0, 3);
        depositPanel.add(amt2, GBconstraints);
        setGBconstraints(GBconstraints, 0, 4);
        depositPanel.add(amt3, GBconstraints);
        setGBconstraints(GBconstraints, 1, 5);
        depositPanel.add(cancel, GBconstraints);
        setGBconstraints(GBconstraints, 1, 2);
        depositPanel.add(amountField, GBconstraints);
        setGBconstraints(GBconstraints, 2, 2);
        depositPanel.add(amt4, GBconstraints);
        setGBconstraints(GBconstraints, 2, 3);
        depositPanel.add(amt5, GBconstraints);
        setGBconstraints(GBconstraints, 2, 4);
        depositPanel.add(amt6, GBconstraints);


        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(depositPanel, GBconstraints);
        amountField.requestFocusInWindow();
        this.setVisible(true);

    }

    private void showDeposit(JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);

        ActionListener list = (ActionEvent e) -> {
            String command = e.getActionCommand();
            int amount = 0;
            switch (command) {
                case "₹100":
                    amount = 100;
                    break;
                case "₹200":
                    amount = 200;
                    break;
                case "₹500":
                    amount = 500;
                    break;
                case "₹1000":
                    amount = 1000;
                    break;
                case "₹2000":
                    amount = 2000;
                    break;
                case "₹5000":
                    amount = 5000;
                    break;
                case "Cancel":
                    showMainMenu(depositPanel);
                    return;
            }
            if (account == 0) {
                setCurrentBalance(getCurrentBalance() + amount);
            } else if (account == 1) {
                setSavingsBalance(getSavingsBalance() + amount);
            }
            showMakeOtherTransaction(depositPanel);
        };


        JLabel l1 = new JLabel("Make Deposit");
        JLabel l2 = new JLabel("Select Amount");

        JButton amt1 = new JButton("₹100");
        amt1.addActionListener(list);
        JButton amt2 = new JButton("₹200");
        amt2.addActionListener(list);
        JButton amt3 = new JButton("₹500");
        amt3.addActionListener(list);
        JButton amt4 = new JButton("₹1000");
        amt4.addActionListener(list);
        JButton amt5 = new JButton("₹2000");
        amt5.addActionListener(list);
        JButton amt6 = new JButton("₹5000");
        amt6.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        JTextField amountField = new JTextField("100", 10);

        depositPanel = new JPanel();
        depositPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.black);
        l2.setForeground(Color.black);
        depositPanel.setSize(new Dimension(150, 150));
        depositPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        depositPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 1, 2);
        depositPanel.add(l2, GBconstraints);
        setGBconstraints(GBconstraints, 0, 3);
        depositPanel.add(amt1, GBconstraints);
        setGBconstraints(GBconstraints, 0, 4);
        depositPanel.add(amt2, GBconstraints);
        setGBconstraints(GBconstraints, 0, 5);
        depositPanel.add(amt3, GBconstraints);
        setGBconstraints(GBconstraints, 1, 6);
        depositPanel.add(cancel, GBconstraints);
        setGBconstraints(GBconstraints, 1, 3);
        depositPanel.add(amountField, GBconstraints);
        setGBconstraints(GBconstraints, 2, 3);
        depositPanel.add(amt4, GBconstraints);
        setGBconstraints(GBconstraints, 2, 4);
        depositPanel.add(amt5, GBconstraints);
        setGBconstraints(GBconstraints, 2, 5);
        depositPanel.add(amt6, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(depositPanel, GBconstraints);
        amountField.requestFocusInWindow();
        this.setVisible(true);
    }

    private void showMakeOtherTransaction(JPanel P2R) {
        if (P2R != null)
            this.remove(P2R);

        ActionListener list = e -> {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                showPinMenu(makeNextPanel, "");
            } else if (command.equals("Cancel")) {
                showFinalPanel(makeNextPanel);
            }
        };

        JLabel l1 = new JLabel("Would you like to do another transaction?");
        JButton OK = new JButton("OK");

        OK.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        makeNextPanel = new JPanel();
        makeNextPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLACK);
        makeNextPanel.setSize(new Dimension(150, 150));
        makeNextPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        makeNextPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 1, 1);
        makeNextPanel.add(cancel, GBconstraints);
        setGBconstraints(GBconstraints, 1, 2);
        makeNextPanel.add(OK, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(makeNextPanel, GBconstraints);
        this.setVisible(true);
    }

    private void showFinalPanel(JPanel P2R) {
        if (P2R != null)
            this.remove(P2R);

        JLabel l1 = new JLabel("Thank you, have a nice day");
        JPanel finalPanel = new JPanel();
        finalPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLUE);
        finalPanel.setSize(new Dimension(150, 150));
        finalPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        finalPanel.add(l1, GBconstraints);
        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(finalPanel, GBconstraints);
        this.setVisible(true);
    }

    private void InsufficientBalance(JPanel P2R) {
        if (P2R != null)
            this.remove(P2R);
        ActionListener list = e -> {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                showPinMenu(makeNextPanel, "");
            } else if (command.equals("Cancel")) {
                showFinalPanel(makeNextPanel);
            }
        };

        JLabel l1 = new JLabel("Insufficient Balance!");
        JLabel l2 = new JLabel("Would you like to continue?");
        JButton OK = new JButton("OK");

        OK.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        makeNextPanel = new JPanel();
        makeNextPanel.setBackground(Color.LIGHT_GRAY);
        l1.setForeground(Color.BLACK);
        l2.setForeground(Color.BLACK);
        makeNextPanel.setSize(new Dimension(150, 150));
        makeNextPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        setGBconstraints(GBconstraints, 1, 0);
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        makeNextPanel.add(l1, GBconstraints);
        setGBconstraints(GBconstraints, 1, 1);
        makeNextPanel.add(l2, GBconstraints);
        setGBconstraints(GBconstraints, 0, 2);
        makeNextPanel.add(cancel, GBconstraints);
        setGBconstraints(GBconstraints, 2, 2);
        makeNextPanel.add(OK, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        setGBconstraints(GBconstraints, 1, 1);
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(makeNextPanel, GBconstraints);
        this.setVisible(true);
    }

    private int getCurrentBalance() {
        return currentBalance;
    }

    private void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    private int getSavingsBalance() {
        return savingsBalance;
    }

    private void setSavingsBalance(int savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    private void setGBconstraints(GridBagConstraints c, int x, int y) {
        c.gridx = x;
        c.gridy = y;
    }

}