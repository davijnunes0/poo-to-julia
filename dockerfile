# ===== Estágio 1: Compilação (Builder) =====
# Usamos uma imagem completa do JDK (Java Development Kit) para ter acesso ao compilador 'javac'.
# Nomeamos este estágio como "builder" para poder nos referir a ele mais tarde.
FROM eclipse-temurin:17-jdk-focal AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia as dependências (a pasta 'lib') para o contêiner.
# Fazemos isso antes para aproveitar o cache do Docker se as dependências não mudarem.
COPY lib ./lib/

# Copia o código-fonte (a pasta 'src') para o contêiner.
COPY src ./src/

# Executa o comando para compilar o código Java.
# -d out: Coloca os arquivos .class compilados em uma pasta chamada 'out'.
# -cp "lib/*": Adiciona todos os arquivos .jar da pasta 'lib' ao classpath de compilação.
RUN javac -d out -cp "lib/*" src/App.java


# ===== Estágio 2: Imagem Final de Produção =====
# Usamos uma imagem JRE (Java Runtime Environment), que é muito menor e mais segura.
FROM eclipse-temurin:17-jre-focal

# Define o diretório de trabalho na imagem final.
WORKDIR /app

# Copia as dependências (pasta 'lib') do estágio "builder" para a imagem final.
# Elas são necessárias para a execução.
COPY --from=builder /app/lib ./lib/

# Copia APENAS o código compilado (pasta 'out') do estágio "builder".
# Não trazemos o código-fonte (.java) nem o JDK para a imagem final.
COPY --from=builder /app/out .

# Define o comando que será executado quando o contêiner iniciar.
# -cp ".:lib/*": Define o classpath de execução.
#   '.': Procura classes no diretório atual (onde está nosso App.class).
#   'lib/*': Inclui todos os .jar da pasta 'lib'.
# App: O nome da classe principal a ser executada.
ENTRYPOINT ["java", "-cp", ".:lib/*", "App"]