package fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.cursospringboot.models.Cliente;

import java.util.UUID;

public class ClienteFixture implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Cliente.class).addTemplate("base", new Rule(){{
            add(Cliente.Fields.id, UUID.randomUUID());
            add(Cliente.Fields.nome, "Ariel");
            add(Cliente.Fields.email, "ariel.pierot@viavarejo.com.br");
        }});

        Fixture.of(Cliente.class).addTemplate("outros").inherits("base", new Rule(){{
            add(Cliente.Fields.nome, "Paulo");
        }});
    }
}