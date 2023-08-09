import { useLocalStorage } from "../hooks/useLocalStorage";
import { Navigate } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";

const PrivateRouteLogin = ({ children }) => {
  const [jwt, setJwt] = useLocalStorage("", "jwt");
  const [isLoading, setIsLoading] = useState(true);
  const [isValid, setIsValid] = useState(null);

  useEffect(() => {
    if (jwt) {
      axios
        .get(`/api/auth/validate?token=${jwt}`, {
          headers: { Authorization: `Bearer ${jwt}` },
        })
        .then((isValid) => {
          console.log("call in priv route");
          setIsValid(isValid);
          setIsLoading(false);
        });
    } else {
      setJwt("");
      setIsLoading(false);
    }
  }, [jwt, setJwt]);

  return isLoading === true ? (
    <div>loading..</div>
  ) : isValid ? (
    children
  ) : (
    <Navigate to={"/login"} />
  );
};
export default PrivateRouteLogin;
