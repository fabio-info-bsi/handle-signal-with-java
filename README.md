# handle-signal-with-java-
Este projeto tem o objetivo de implementar código Java para controlar handle-signal (refere-se ao processo de capturar e responder a sinais (signals) enviados ao processo da aplicação. Sinais são mecanismos utilizados pelo sistema operacional para comunicar eventos assíncronos aos processos em execução).

---
# Sinais
`SIGTERM`, `SIGINT`, e `SIGKILL` são sinais usados em sistemas Unix-like para controlar o comportamento de processos. Eles diferem em propósito, comportamento e como podem ser manipulados pelo processo.

### 1. **`SIGINT` (Signal Interrupt - Sinal 2)**

- **Origem:** Normalmente gerado quando o usuário pressiona `Ctrl+C` no terminal onde o processo está em execução.
- **Finalidade:** Solicitar a interrupção do processo. É um pedido amigável para o processo parar o que está fazendo.
- **Comportamento Padrão:** O processo é interrompido (termina), mas ele pode capturar esse sinal e executar código específico antes de encerrar.
- **Captação e Manipulação:** O processo pode capturar e tratar o sinal, permitindo que execute alguma limpeza ou outras operações antes de encerrar. Se o sinal não for tratado, o processo será encerrado.

### 2. **`SIGTERM` (Signal Terminate - Sinal 15)**

- **Origem:** Pode ser enviado por qualquer processo ou pelo sistema operacional usando o comando `kill`, sem parâmetros adicionais.
- **Finalidade:** Solicitar que o processo termine de maneira graciosa. É o sinal padrão quando você usa `kill <PID>` sem especificar um sinal.
- **Comportamento Padrão:** O processo é encerrado, mas de maneira semelhante ao `SIGINT`, ele pode capturar e tratar o sinal, permitindo a execução de operações de limpeza antes do encerramento.
- **Captação e Manipulação:** Pode ser capturado e manipulado pelo processo para realizar uma terminação ordenada. Isso é comum em servidores que precisam liberar recursos, salvar estado, etc., antes de encerrar.

### 3. **`SIGKILL` (Signal Kill - Sinal 9)**

- **Origem:** Enviado pelo comando `kill -9 <PID>` ou `kill -SIGKILL <PID>`.
- **Finalidade:** Forçar o término imediato de um processo. Diferente de `SIGINT` e `SIGTERM`, `SIGKILL` é uma ordem para o sistema operacional matar o processo instantaneamente.
- **Comportamento Padrão:** O processo é imediatamente encerrado pelo sistema operacional sem a chance de executar qualquer limpeza, liberar recursos ou salvar o estado. Todas as threads no processo são terminadas, e os recursos são liberados pelo sistema operacional.
- **Captação e Manipulação:** `SIGKILL` não pode ser capturado, ignorado ou manipulado pelo processo. É um sinal que força o término incondicional do processo, o que significa que o processo não tem a chance de realizar qualquer operação antes de ser finalizado.

### Comparação Resumida:

| Sinal   | Código | Finalidade                  | Comportamento Padrão                  | Pode ser Capturado/Manipulado? |
|---------|--------|-----------------------------|---------------------------------------|--------------------------------|
| `SIGINT`| 2      | Interromper amigavelmente   | Encerra o processo                    | Sim                            |
| `SIGTERM`| 15    | Solicitar término gracioso  | Encerra o processo                    | Sim                            |
| `SIGKILL`| 9     | Forçar término imediato     | Mata o processo instantaneamente      | Não                            |

### Uso Comum:

- **`SIGINT`:** Usado principalmente para interrupções manuais durante a execução de um programa, especialmente durante o desenvolvimento ou operação em linha de comando.
- **`SIGTERM`:** Usado para encerrar processos de maneira ordenada e é comum em scripts de automação e durante o gerenciamento de processos em sistemas.
- **`SIGKILL`:** Usado para encerrar processos que não estão respondendo ou que precisam ser forçados a terminar imediatamente, sem chance de recuperação ou tratamento.

### Considerações:

- Use **`SIGINT`** e **`SIGTERM`** quando quiser dar ao processo a oportunidade de encerrar graciosamente.
- Use **`SIGKILL`** apenas como último recurso, pois o processo não terá a oportunidade de limpar recursos ou salvar dados antes de ser finalizado.

### Exemplo:

```
kill -SIGINT <PID>
```

```
kill -SIGTERM <PID>
```

```
kill -9 <PID> or kill SIGKILL <PID>
```