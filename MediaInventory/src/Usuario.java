
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ellia
 */
class Usuario {
    private String nome;
    private ArrayList<Midia> midias;
    private ArrayList<String> relacoes;
    private ArrayList<Boolean> possui;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Midia> wishlist;
    
    public Usuario(String nome) {
        this.nome = nome;
        this.midias = new ArrayList<>();
        this.relacoes = new ArrayList<>();
        this.wishlist = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.possui = new ArrayList<>();
    }
    
    public void addMidia(Midia midia, String relacao, Boolean possui) {
        midias.add(midia);
        relacoes.add(relacao);
        this.possui.add(possui);
    }
    
    public void addDesejo(Midia midia_desejada) {
        this.wishlist.add(midia_desejada);
    }
    
    public void addEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }
    
    public String getNomeUsuario() {
        return nome;
    }
    
    public void listarEmprestimos(int data_atual) {
        if(emprestimos.size() < 1) {
            System.out.println("Nao ha midias emprestadas");
            System.out.println("---------------------------------");
        } else {
            System.out.println("Lista das midias emprestadas: ");
            System.out.println("----------------");
            for(int i = 0; i < emprestimos.size(); i++) {
                emprestimos.get(i).exibirEmprestimo(data_atual);
            }
        } 
    }
    
    public void listarPorDirigente(String dirigente) {
        System.out.println("Listagem das obras de " + dirigente + " :");
        for(int i = 0; i < midias.size(); i++) {
            if(dirigente.equals(midias.get(i).getDirigente(dirigente))){
                System.out.println("* " + midias.get(i).getNome() + ";");
            }
        }
        System.out.println("---------------------------------");
    }
    
    public void mostrarWishList() {
        System.out.println("Minha lista de desejos:");
        System.out.println("---------------------------------");
        for(int i = 0; i < wishlist.size(); i ++) {
            System.out.println(wishlist.get(i).getNome() + ", R$" + wishlist.get(i).getPreco());
        }
        System.out.println("---------------------------------");
    }
    
    public void estatisticasGerais() {
        float horas_gastas = 0;
        for(int i = 0; i < midias.size(); i++){
            if (relacoes.get(i).equals("b")) {
                horas_gastas += midias.get(i).getHoras();
            }
        }
        System.out.println("Horas totais gastas com midias consumidas : " + horas_gastas);
        
        float horas_gastas_outros = 0;
        for(int i = 0; i < midias.size(); i++){
            if (relacoes.get(i).equals("a") || relacoes.get(i).equals("a")) {
                horas_gastas_outros += midias.get(i).getHoras();
            }
        }
        System.out.println("Horas necessarias para consumir as demais midias: " + horas_gastas_outros);
        
  
        System.out.println("---------------------------------");
        HashMap<String, Integer> generoTotals = new HashMap<>();

        for (Midia midia : midias) {
            String genero_aux = midia.getGenero();

            if (!generoTotals.containsKey(genero_aux)) {
                generoTotals.put(genero_aux, 0);
            }

            generoTotals.put(genero_aux, generoTotals.get(genero_aux) + 1);
        }

        for (String genero : generoTotals.keySet()) {
            float total = generoTotals.get(genero);
            System.out.println("Total por genero (" + genero + "): " + total);
        }

        System.out.println("---------------------------------");
        
        int total_livro = 0;
        int total_serie = 0;
        int total_filme = 0;
        
        for(int i = 0; i < midias.size(); i++) {
            if ("livro".equals(midias.get(i).getTipo())) total_livro += 1;
            if ("serie".equals(midias.get(i).getTipo())) total_serie += 1;
            if ("filme".equals(midias.get(i).getTipo())) total_filme += 1;
        }
        
        System.out.println("Total de livros: " + total_livro);
        System.out.println("Total de series: " + total_serie);
        System.out.println("Total de filmes: " + total_filme);
        
        System.out.println("---------------------------------");
        
        System.out.println("Quantidade de temporadas de cada serie que se possui e que ja se consumiu: ");
        System.out.println("---------------------------------");
        for(int i = 0; i < midias.size(); i++) {
            if("b".equals(relacoes.get(i)) && possui.get(i) == true && midias.get(i).getTipo().equals("serie")) {
                String nome_serie = midias.get(i).getNome();
                int quant_temp = midias.get(i).getNmrTemp();
                
                System.out.println("Serie: " + nome_serie + ", quantidade de temporadas: " + quant_temp);
                System.out.println("---------------------------------");
            }
        }
    }
}
