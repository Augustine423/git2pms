const authUrl="http://3.34.199.155:8081/api/auth"

export const login = (data) => {

    return fetch(`${authUrl}/login`, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    });
  };
  
  