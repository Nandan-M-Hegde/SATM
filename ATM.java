
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Nandan on 06-11-2017.
 */
public class ATM extends JFrame {

    private JPanel LPanel;
    private JPasswordField pw;
    private JPanel PWPanel;
    private int COp;
    private JPanel SoCPanel;
    private JPanel mainPanel;
    private JPanel depositPanel;
    private JTextField amountFieldText;
    private JPanel balancePanel;
    private JPanel makeNextPanel;
    private BankAcc bankAcc;



    public ATM() {
        super("ATM");
        bankAcc = new BankAcc();
        dispMenu(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void dispMenu(JPanel P2R) {
        if (P2R != null)
            this.remove(P2R);

        JLabel l = new JLabel();
        l.setText("Welcome dear user");
        l.setForeground(Color.BLACK);

        JButton cont = new JButton("Continue");
        JButton cancel = new JButton("Cancel");

        ActionListener list = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Cancel")) {
                    showFinalPanel(LPanel);
                    return;
                }
                showPinMenu(LPanel, "");
            }
        };
        cont.addActionListener(list);

        LPanel = new JPanel();
        LPanel.setBackground(Color.white);
        LPanel.setSize(new Dimension(150, 150));
        LPanel.setLayout(new GridBagLayout());

        GridBagConstraints GBconstraints = new GridBagConstraints();
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        LPanel.add(l, GBconstraints);

        GBconstraints.gridx = 0;
        GBconstraints.gridy = 1;
        LPanel.add(cont, GBconstraints);

        cancel.addActionListener(list);
        GBconstraints.gridy = 2;
        LPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;

        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(LPanel, GBconstraints);
        this.setVisible(true);
    }

    public void showPinMenu(JPanel P2R, String ErrorMsg) {
        if (P2R != null)
            this.remove(P2R);
        JLabel l1 = new JLabel("Enter your 4 digit PIN");
        JLabel l2 = new JLabel("(PIN is 1111)");
        pw = new JPasswordField(4);
        pw.setFocusable(true);
        pw.requestFocusInWindow();
        JButton OK = new JButton("Okay");
        final int[] count = {0};
        ActionListener list = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pin = new String(ATM.this.pw.getPassword());
                if (pin.equals("1111")) {
                    showMainMenu(PWPanel);
                    count[0]++;
                } else if (count[0] < 3)
                    showPinMenu(PWPanel, "Error, You entered a wrong password!");
                else {
                    showFinalPanel(LPanel);
                    return;
                }
            }
        };
        OK.addActionListener(list);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispMenu(PWPanel);
            }
        });

        PWPanel = new JPanel();
        PWPanel.setBackground(Color.WHITE);
        l1.setForeground(Color.BLACK);
        PWPanel.setSize(new Dimension(150, 150));
        PWPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        if (!ErrorMsg.equals("")) {
            JLabel error = new JLabel("You entered a wrong PIN");
            error.setForeground(Color.RED);
            PWPanel.add(error, GBconstraints);
        }
        GBconstraints.gridy = 1;
        PWPanel.add(l1, GBconstraints);
        GBconstraints.gridy = 2;
        PWPanel.add(l2, GBconstraints);
        GBconstraints.gridy = 3;
        PWPanel.add(pw, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 4;
        PWPanel.add(OK, GBconstraints);
        GBconstraints.gridy = 5;
        PWPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(PWPanel, GBconstraints);
        pw.setFocusable(true);
        pw.requestFocusInWindow();
        getRootPane().setDefaultButton(OK);
        this.setVisible(true);
    }

    public void showMainMenu(JPanel P2R) {
        if (P2R != null) {
            this.remove(P2R);
        }
        ActionListener list = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("Deposit")) {
                    SoC(mainPanel, 0);
                } else if (cmd.equals("Withdraw")) {
                    SoC(mainPanel, 1);
                } else if (cmd.equals("Get Balance")) {
                    SoC(mainPanel, 2);
                } else if (cmd.equals("Transfer")) {
                    SoC(mainPanel, 3);
                } else if (cmd.equals("Cancel")) {
                    showFinalPanel(mainPanel);
                }
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
        mainPanel.setBackground(Color.WHITE);
        l1.setForeground(Color.BLACK);
        mainPanel.setSize(new Dimension(150, 150));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(l1, GBconstraints);
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 1;
        mainPanel.add(deposit, GBconstraints);
        GBconstraints.gridx = 2;
        mainPanel.add(withdraw, GBconstraints);
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 2;
        mainPanel.add(balance, GBconstraints);
        GBconstraints.gridx = 2;
        mainPanel.add(transfer, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 3;
        mainPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(mainPanel, GBconstraints);
        this.setVisible(true);
    }

    private void SoC(JPanel P2R, int op) {
        if (P2R != null)
            this.remove(P2R);
// set the current operation
        COp = op;
// create the action listener which will respond to the actions of pressing the buttons
        ActionListener list = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                int account = 0;
                if (cmd.equals("Current")) {
                    account = 0;
                } else if (cmd.equals("Savings")) {
                    account = 1;
                } else if (cmd.equals("From Savings")) {
                    account = 0;
                } else if (cmd.equals("From Current")) {
                    account = 1;
                } else if (cmd.equals("Cancel")) {
                    showMainMenu(SoCPanel);
                    return;
                }
                if (ATM.this.COp == 0)
                    showDeposit(SoCPanel, account);
                else if (ATM.this.COp == 1)
                    showWithdraw(SoCPanel, account);
                else if (ATM.this.COp == 2)
                    showBalance(SoCPanel, account);
                else if (ATM.this.COp == 3)
                    showTransfer(SoCPanel, account);
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
        SoCPanel.setBackground(Color.WHITE);
        l1.setForeground(Color.BLACK);
        SoCPanel.setSize(new Dimension(150, 150));
        SoCPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        SoCPanel.add(l1, GBconstraints);
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 2;
        SoCPanel.add(current, GBconstraints);
        GBconstraints.gridx = 2;
        GBconstraints.gridy = 2;
        SoCPanel.add(savings, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 3;
        SoCPanel.add(cancel, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(SoCPanel, GBconstraints);
        this.setVisible(true);
    }

    public void showTransfer(JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);
        int currentAccount = account;
        ActionListener list = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                int amount = 0;

                if (cmd.equals("₹100")) {
                    amount = 100;
                } else if (cmd.equals("₹200")) {
                    amount = 200;
                } else if (cmd.equals("₹500")) {
                    amount = 500;
                } else if (cmd.equals("₹1000")) {
                    amount = 1000;
                } else if (cmd.equals("₹2000")) {
                    amount = 2000;
                } else if (cmd.equals("₹5000")) {
                    amount = 5000;
                } else if (cmd.equals("OK")) {
                    try {
                        amount = Integer.parseInt(ATM.this.amountFieldText.getText());
                    } catch (NumberFormatException nf) {
                        return;
                    }
                } else if (cmd.equals("Cancel")) {
                    showMainMenu(depositPanel);
                    return;
                }
                if (currentAccount == 0) {
                    bankAcc.setCurrentBalance(bankAcc.getCurrentBalance() - amount);
                    bankAcc.setSavingsBalance(bankAcc.getSavingsBalance() + amount);
                } else if (currentAccount == 1) {
                    bankAcc.setCurrentBalance(bankAcc.getCurrentBalance() + amount);
                    bankAcc.setSavingsBalance(bankAcc.getSavingsBalance() - amount);
                }
                showMakeOtherTransaction(depositPanel);
            }
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
        JButton OK = new JButton("OK");
        OK.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        JTextField amtField = new JTextField(10);
        depositPanel = new JPanel();
        depositPanel.setBackground(Color.WHITE);
        l1.setForeground(Color.BLACK);
        depositPanel.setSize(new Dimension(150, 150));
        depositPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        depositPanel.add(l1, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        depositPanel.add(l1, GBconstraints);
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 2;
        depositPanel.add(amt1, GBconstraints);
        GBconstraints.gridy = 3;
        depositPanel.add(amt2, GBconstraints);
        GBconstraints.gridy = 4;
        depositPanel.add(amt3, GBconstraints);
        GBconstraints.gridy = 5;
        depositPanel.add(cancel, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 2;
        depositPanel.add(amtField, GBconstraints);
        GBconstraints.gridx = 2;
        GBconstraints.gridy = 2;
        depositPanel.add(amt4, GBconstraints);
        GBconstraints.gridy = 3;
        depositPanel.add(amt5, GBconstraints);
        GBconstraints.gridy = 4;
        depositPanel.add(amt6, GBconstraints);
        GBconstraints.gridy = 5;
        depositPanel.add(OK, GBconstraints);
// add the panel to the main frame
        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;

        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(depositPanel, GBconstraints);
        amtField.setFocusable(true);
        amtField.requestFocusInWindow();
        this.setVisible(true);
    }

    public void showBalance (JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);
        int currentAccount = account;
        JLabel label = new JLabel(("Account Balance ") + ((currentAccount == 0) ? "Current" : "Savings"));

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMakeOtherTransaction(balancePanel);
            }
        });
        amountFieldText = new JTextField(10);
        if (currentAccount == 0) {
            amountFieldText.setText("₹ " + bankAcc.getCurrentBalance() + " ");
        } else if (currentAccount == 1) {
            amountFieldText.setText("₹ " + bankAcc.getSavingsBalance() + " ");
        }
        amountFieldText.setEditable(false);

        balancePanel = new JPanel();
        balancePanel.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        balancePanel.setSize(new Dimension(150, 150));
        balancePanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        balancePanel.add(label, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 2;
        balancePanel.add(amountFieldText, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 3;
        balancePanel.add(ok, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(balancePanel, GBconstraints);
        this.setVisible(true);
    }

    public void showWithdraw (JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);
        int currentAccount = account;

        ActionListener list = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                int amount = 0;
                if (command.equals("₹100")) {
                    amount = 100;
                } else if (command.equals("₹200")) {
                    amount = 200;
                } else if (command.equals("₹500")) {
                    amount = 500;
                } else if (command.equals("₹1000")) {
                    amount = 1000;
                } else if (command.equals("₹2000")) {
                    amount = 2000;
                } else if (command.equals("₹5000")) {
                    amount = 5000;
                } else if (command.equals("OK")) {
                    try {
                        amount = Integer.parseInt(ATM.this.amountFieldText.getText());
                    } catch (NumberFormatException nf) {
                        return;
                    }
                } else if (command.equals("Cancel")) {
                    showMainMenu(depositPanel);
                    return;
                }
                if (currentAccount == 0) {
                    bankAcc.setCurrentBalance(bankAcc.getCurrentBalance() - amount);
                } else if (currentAccount == 1) {
                    bankAcc.setSavingsBalance(bankAcc.getSavingsBalance() - amount);
                }
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
        JButton ok = new JButton("OK");
        ok.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        JTextField amountField = new JTextField(10);

        depositPanel = new JPanel();
        depositPanel.setBackground(Color.WHITE);
        l1.setForeground(Color.BLACK);
        l2.setForeground(Color.BLACK);
        depositPanel.setSize(new Dimension(150, 150));
        depositPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        depositPanel.add(l1, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        depositPanel.add(l2, GBconstraints);
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 2;
        depositPanel.add(amt1, GBconstraints);
        GBconstraints.gridy = 3;
        depositPanel.add(amt2, GBconstraints);
        GBconstraints.gridy = 4;
        depositPanel.add(amt3, GBconstraints);
        GBconstraints.gridy = 5;
        depositPanel.add(cancel, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 2;
        depositPanel.add(amountField, GBconstraints);
        GBconstraints.gridx = 2;
        GBconstraints.gridy = 2;
        depositPanel.add(amt4, GBconstraints);
        GBconstraints.gridy = 3;
        depositPanel.add(amt5, GBconstraints);
        GBconstraints.gridy = 4;
        depositPanel.add(amt6, GBconstraints);
        GBconstraints.gridy = 5;
        depositPanel.add(ok, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(depositPanel, GBconstraints);
        amountField.requestFocusInWindow();
        this.setVisible(true);
    }

    public void showDeposit (JPanel P2R, int account) {
        if (P2R != null)
            this.remove(P2R);
        int currentAccount = account;

        ActionListener list = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                int amount = 0;
                if (command.equals("₹100")) {
                    amount = 100;
                } else if (command.equals("₹200")) {
                    amount = 200;
                } else if (command.equals("₹500")) {
                    amount = 500;
                } else if (command.equals("₹1000")) {
                    amount = 1000;
                } else if (command.equals("₹2000")) {
                    amount = 2000;
                } else if (command.equals("₹5000")) {
                    amount = 5000;
                } else if (command.equals("OK")) {
                    try {
                        amount = Integer.parseInt(ATM.this.amountFieldText.getText());
                    } catch (NumberFormatException nf) {
                        return;
                    }
                } else if (command.equals("Cancel")) {
                    showMainMenu(depositPanel);
                    return;
                }
                if (currentAccount == 0) {
                    bankAcc.setCurrentBalance(bankAcc.getCurrentBalance() + amount);
                } else if (currentAccount == 1) {
                    bankAcc.setSavingsBalance(bankAcc.getSavingsBalance() + amount);
                }
                showMakeOtherTransaction(depositPanel);
            }
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
        JButton ok = new JButton("OK");
        ok.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        JTextField amountField = new JTextField(10);

        depositPanel = new JPanel();
        depositPanel.setBackground(Color.white);
        l1.setForeground(Color.black);
        l2.setForeground(Color.black);
        depositPanel.setSize(new Dimension(150, 150));
        depositPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        depositPanel.add(l1, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        depositPanel.add(l2, GBconstraints);
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 2;
        depositPanel.add(amt1, GBconstraints);
        GBconstraints.gridy = 3;
        depositPanel.add(amt2, GBconstraints);
        GBconstraints.gridy = 4;
        depositPanel.add(amt3, GBconstraints);
        GBconstraints.gridy = 5;
        depositPanel.add(cancel, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 2;
        depositPanel.add(amountField, GBconstraints);
        GBconstraints.gridx = 2;
        GBconstraints.gridy = 2;
        depositPanel.add(amt4, GBconstraints);
        GBconstraints.gridy = 3;
        depositPanel.add(amt5, GBconstraints);
        GBconstraints.gridy = 4;
        depositPanel.add(amt6, GBconstraints);
        GBconstraints.gridy = 5;
        depositPanel.add(ok, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(depositPanel, GBconstraints);
        amountField.requestFocusInWindow();
        this.setVisible(true);
    }

    public void showMakeOtherTransaction(JPanel P2R) {
        if (P2R != null)
            this.remove(P2R);

        ActionListener list = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("OK")) {
                    showPinMenu(makeNextPanel, "");
                } else if (command.equals("Cancel")) {
                    showFinalPanel(makeNextPanel);
                }
            }
        };

        JLabel l1 = new JLabel("Would you like to do another transaction?");
        //JLabel l2 = new JLabel(messages.getMessage("MAKE_NEXT1", language));
        JButton OK = new JButton("OK");

        OK.addActionListener(list);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(list);

        makeNextPanel = new JPanel();
        makeNextPanel.setBackground(Color.WHITE);
        l1.setForeground(Color.BLACK);
        makeNextPanel.setSize(new Dimension(150, 150));
        makeNextPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        makeNextPanel.add(l1, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        makeNextPanel.add(cancel, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 2;
        makeNextPanel.add(OK, GBconstraints);

        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
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
        finalPanel.setBackground(Color.WHITE);
        l1.setForeground(Color.GREEN);
        finalPanel.setSize(new Dimension(150, 150));
        finalPanel.setLayout(new GridBagLayout());
        GridBagConstraints GBconstraints = new GridBagConstraints();

        GBconstraints.gridx = 1;
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(10, 10, 10, 10);
        finalPanel.add(l1, GBconstraints);
// add the panel to the main frame
        this.getContentPane().setLayout(new GridBagLayout());
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 150;
        GBconstraints.weighty = 150;
        this.getContentPane().add(finalPanel, GBconstraints);
        this.setVisible(true);
    }
}
