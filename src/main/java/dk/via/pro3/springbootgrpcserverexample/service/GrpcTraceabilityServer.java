package dk.via.pro3.springbootgrpcserverexample.service;

import dk.via.pro3.generated.AnimalRegistrationNumbersResponse;
import dk.via.pro3.generated.GetAnimalsForProductRequest;
import dk.via.pro3.generated.GetProductsForAnimalRequest;
import dk.via.pro3.generated.ProductIdsResponse;
import dk.via.pro3.generated.TraceabilityServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class GrpcTraceabilityServer
    extends TraceabilityServiceGrpc.TraceabilityServiceImplBase
{
  private final TraceabilityService traceabilityService;

  public GrpcTraceabilityServer(
      TraceabilityService traceabilityService)
  {
    this.traceabilityService = traceabilityService;
  }

  @Override
  public void getAnimalsForProduct(
      GetAnimalsForProductRequest request,
      StreamObserver<AnimalRegistrationNumbersResponse> responseObserver)
  {
    List<String> animalRegistrationNumbers =
        traceabilityService.getAnimalsForProduct(
            request.getProductId());

    AnimalRegistrationNumbersResponse response =
        AnimalRegistrationNumbersResponse.newBuilder()
            .addAllAnimalRegistrationNumbers(
                animalRegistrationNumbers)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getProductsForAnimal(
      GetProductsForAnimalRequest request,
      StreamObserver<ProductIdsResponse> responseObserver)
  {
    List<String> productIds =
        traceabilityService.getProductsForAnimal(
            request.getAnimalRegistrationNumber());

    ProductIdsResponse response =
        ProductIdsResponse.newBuilder()
            .addAllProductIds(productIds)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
