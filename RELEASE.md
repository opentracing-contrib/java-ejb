# OpenTracing Java EJB release process

For now, just provided you have write access to the main branch, run:

```console
mvn relese:prepare
```

The version will be bumped to the final version, tagged, and a new development version will be committed. The GitHub action will take care of building and releasing the new version based on the new tag.
