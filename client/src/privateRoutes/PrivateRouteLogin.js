import { useLocalStorage } from "../hooks/useLocalStorage";
import { Navigate } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";

const PrivateRouteLogin = ({ children }) => {
  const [jwt, setJwt] = useLocalStorage("", "jwt");
  const [isLoading, setIsLoading] = useState(true);
  const [isValid, setIsValid] = useState(null);
  console.log("compoent");

  useEffect(() => {
    if (jwt) {
      console.log("outfetch");
      axios
        .get(`/api/auth/validate?token=${jwt}`, {
          headers: { Authorization: `Bearer ${jwt}` },
        })
        .then((isValid) => {
          console.log(isValid);
          setIsValid(isValid);
          setIsLoading(false);
        });
    } else {
      setIsLoading(false);
    }
  }, [jwt]);

  return isLoading === true ? (
    <div>loading..</div>
  ) : isValid ? (
    children
  ) : (
    <Navigate to={"/login"} />
  );
};
export default PrivateRouteLogin;
