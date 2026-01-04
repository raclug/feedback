package br.com.feedbacks.services.impl;

import br.com.feedbacks.dtos.AvaliacaoDTO;
import br.com.feedbacks.mappers.AvaliacaoMapper;
import br.com.feedbacks.services.EnviarAvaliacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvocationType;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class EnviarAvaliacaoServiceImpl implements EnviarAvaliacaoService {

    @Value("${aws.lambda.target-function-name}")
    private String targetFunctionName;

    private final LambdaClient lambdaClient;

    private final AvaliacaoMapper avaliacaoMapper;

    public EnviarAvaliacaoServiceImpl(LambdaClient lambdaClient,
                                      AvaliacaoMapper avaliacaoMapper) {
        this.lambdaClient = lambdaClient;
        this.avaliacaoMapper = avaliacaoMapper;
    }

    public void enviarAvaliacao(AvaliacaoDTO avaliacao) {
        try {
            log.info("Invocando lambda de analise de avaliação");

            var payload = avaliacaoMapper.toString(avaliacao);

            InvokeRequest request = InvokeRequest.builder()
                    .functionName(targetFunctionName)
                    .invocationType(InvocationType.REQUEST_RESPONSE)
                    .payload(SdkBytes.fromByteArray(payload.getBytes(StandardCharsets.UTF_8)))
                    .build();

            InvokeResponse resp = lambdaClient.invoke(request);

            log.info("Lambda invocada (statusCode={}): {}", resp.statusCode(), targetFunctionName);

        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar AvaliacaoDTO", e);
            throw new RuntimeException(e);
        } catch (Exception ex) {
            log.error("Falha ao invocar lambda de forma síncrona", ex);
            throw new RuntimeException(ex);
        }
    }
}
