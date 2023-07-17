package local.mathewdj.rock.graphql

import com.netflix.graphql.dgs.DgsScalar
import graphql.language.StringValue
import graphql.language.Value
import graphql.schema.Coercing
import graphql.schema.CoercingSerializeException
import java.util.UUID

@DgsScalar(name="UUID")
class UUIDScalar : Coercing<UUID, String> {
    override fun serialize(dataFetcherResult: Any): String? {
        if (dataFetcherResult is UUID) {
            return dataFetcherResult.toString()
        } else {
            throw CoercingSerializeException("Not a valid UUID");
        }
    }

    override fun parseValue(input: Any): UUID? {
        return UUID.fromString(input.toString())
    }

    override fun parseLiteral(input: Any): UUID? {
        return UUID.fromString(input.toString())
    }

    override fun valueToLiteral(input: Any): Value<out Value<*>> {
        return StringValue(input.toString())
    }
}
