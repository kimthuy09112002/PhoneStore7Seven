package controller.admin;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ordered;
import model.Product;
import model.Transactions;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;
import utils.OrderStatus;

public class AdminHomePage extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TransactionService transactionService = new TransactionServicesImpl();
    OrderedService catalogService = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();

    public AdminHomePage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transactions> list_trans = transactionService.getAll();
        List<Ordered> list_ordered = catalogService.getAll();
        List<Product> list_product = productService.getAll();
        String department = (String) request.getSession().getAttribute("department_id");
        int order = transactionService.getTotalTransactions(department);
        long soldPrice = 0;
        int sumTrans = 0;
        long quantitySold = 0;
        List<Transactions> transactionsSoldList = new ArrayList<>();
        List<Ordered> listOrderedPaid = new ArrayList<>();
        long priceIphone = 0;
        long priceSamsung = 0;
        long priceOppo = 0;
        long priceXiaomi = 0;
        long priceVivo = 0;
        long priceOther = 0;
        for (Transactions a : list_trans) {
            int id = a.getId();
            String x = a.getStatus();
            if (x == null) {
                x = "";
            }
            if (x.equals(OrderStatus.DONE) && a.getDepartment_id().equals(department)) {
                for (Ordered b : list_ordered) {
                    int orderedID = Integer.parseInt(b.getTransaction_id());
                    if (id == orderedID) {
                        listOrderedPaid.add(b);
                    }
                }
            }
        }
        for (Product a : list_product) {
            String catalog = a.getCatalog_id();
            String id = a.getId();
            int productID = Integer.parseInt(id);
            int catalog_id = Integer.parseInt(catalog);
            if (catalog_id == 1) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceIphone += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 2) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceSamsung += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 3) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceOppo += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 4) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceXiaomi += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 5) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceVivo += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 6) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceOther += totalPriceAfterSales;
                    }
                }
            }
        }
        for (Transactions a : list_trans) {
            String x = a.getStatus();
            if (x == null) {
                x = "";
            }
            if (x.equals(OrderStatus.DONE) && a.getDepartment_id().equals(department)) {
                long price = Long.parseLong(a.getAmount().replace(".", ""));
                soldPrice += price;
                sumTrans++;
                transactionsSoldList.add(a);
            }
        }
        
        
        for (Ordered a : list_ordered) {
            int orderedID = Integer.parseInt(a.getTransaction_id());
            for (Transactions b : list_trans) {
                if (orderedID == b.getId() && (b.getStatus().equals(OrderStatus.DOING) || b.getStatus().equals(OrderStatus.DONE)) 
                        && b.getDepartment_id().equals(department)) {
                    Product product = productService.get(Integer.parseInt(a.getProduct_id()));
                    long sold = product.getSold();
                    if (sold > 0) {
                        quantitySold += a.getQty();
                    } else {
                        quantitySold = a.getQty();
                    }
                }
            }
        }
        request.setAttribute("transactions", list_trans);
        request.setAttribute("sold-transactions", transactionsSoldList);
        request.setAttribute("ordered", list_ordered);
        request.setAttribute("place-order", order);
        request.setAttribute("sold-sum", sumTrans);
        request.setAttribute("sold-quantity", quantitySold);

        request.setAttribute("sold-iphone", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceIphone)));
        request.setAttribute("sold-samsung", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceSamsung)));
        request.setAttribute("sold-oppo", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceOppo)));
        request.setAttribute("sold-xiaomi", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceXiaomi)));
        request.setAttribute("sold-vivo", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceVivo)));
        request.setAttribute("sold-other", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceOther)));

        request.setAttribute("sold-price", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(soldPrice)));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/index.jsp");
        dispatcher.forward(request, response);
    }
    
    
}
