# ===== Estágio 1: Compilação (Builder) =====
# Usamos uma imagem completa do JDK (Java Development Kit) para ter acesso ao compilador 'javac'.
# Nomeamos este estágio como "builder" para poder nos referir a ele mais tarde.
FROM eclipse-temurin:17-jdk-focal AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia as dependências (a pasta 'lib') para o contêiner.
COPY lib ./lib/

# Copia o código-fonte (a pasta 'src') para o contêiner.
COPY src ./src/

# Executa o comando para compilar o código Java.
RUN find src -name "*.java" > sources.txt && javac -d out -cp "lib/*" @sources.txt

# ===== Estágio 2: Imagem Final de Produção =====
# Usamos uma imagem JRE (Java Runtime Environment), que é muito menor e mais segura.
FROM eclipse-temurin:17-jre-focal

# --- ESSA É A LINHA QUE CORRIGE O ERRO ---
# Instala as bibliotecas gráficas do Linux (X11) que o Java Swing/AWT precisa para rodar.
RUN apt-get update && apt-get install -y libxext6 libx11-6 libxi6 libxrender1 libxtst6 && rm -rf /var/lib/apt/lists/*

# Define o diretório de trabalho na imagem final.
WORKDIR /app

# Copia as dependências (pasta 'lib') do estágio "builder" para a imagem final.
COPY --from=builder /app/lib ./lib/

# Copia APENAS o código compilado (pasta 'out') do estágio "builder".
COPY --from=builder /app/out .

# Define o comando que será executado quando o contêiner iniciar.
ENTRYPOINT ["java", "-cp", ".:lib/*", "App"]
