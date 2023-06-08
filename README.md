### Atipera interview task

This api returning the repository and their last commit from Github by user name.

Api use Github api to getting information about repositories and their branches with last commit sha.

I used WebClient form Spring WebFlux with async function to performance, for more transparent code i used lombok.

Project has one endpoint

GET .../api/users/{userName}/repositories

<h1>Success response for my github account:</h1>
> Response 200 Status OK

### URL

```
    localhost:8080/api/users/Oziaka/repositories
```

### Header

```
    Accept: application/json
```

### Response body

```json
[
  {
    "name": "address-calculator-api",
    "ownerLogin": "Oziaka",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "f1cd510d0344db070bcca262d1561e9aa3f47ac3"
      }
    ]
  },
  {
    "name": "atipera-task",
    "ownerLogin": "Oziaka",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "5018283abc519aeb3caf7eb999f3be0cdcd3b1e8"
      }
    ]
  },
  {
    "name": "home-budget-api",
    "ownerLogin": "Oziaka",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "4f1bf126699abcb486f9c2c3ae856272e42a9953"
      },
      {
        "name": "java-clean-architecture_",
        "lastCommitSha": "ce6f632e9e2944f0a3d04d39bd0ed166186818a3"
      },
      {
        "name": "slave",
        "lastCommitSha": "2c1102b12552388f7c9fb7184dc170bd4b78876a"
      },
      {
        "name": "dependabot/maven/org.postgresql-postgresql-42.4.1",
        "lastCommitSha": "e4ec7fc9aab3fea70930fecc14c8cef734a396d8"
      }
    ]
  },
  ...
]
```
<h1>Failure response:</h1>
<h2>When header accept xml</h2>

> Response 406 Status Not Acceptable

### URL

```
    localhost:8080/api/users/Oziaka/repositories
```

### Header

```
    Accept: application/xml
```

### Body

```json
{
  "status": 406,
  "message": "No acceptable representation"
}
```

<h2>When user not exist</h2>

> Response 404 Status Not Found

### URL

```
    localhost:8080/api/users/fasdfasdfasdf/repositories
```

### Header

```
    Accept: application/json
```

### Response body

```json
{
  "status": 404,
  "message": "User not found"
}
```






