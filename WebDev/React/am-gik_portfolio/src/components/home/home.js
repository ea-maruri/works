import React from "react";
import "./home.css";

// Components
import App from "./../../app/App";
import PseudoNav from "./../pseudo-nav/pseudo-nav";
import Footer from "./../footer/footer";

// Images
import Me from "./Me.jpg";

class Home extends React.Component {
  render() {
    return (
      <div>
        <App />

        <section>
          <main>
            <div className="container mt-5">
              <div className="jumbotron">
                <div className="pool-box mt-5">
                  <h1 class="msuper-title">
                    We all HAVE<br></br>MAGIC<br></br>WITHIN US
                  </h1>
                </div>
              </div>
            </div>
          </main>
        </section>

        <PseudoNav />

        <main>
          <div className="container bottom-space">
            <div className="row">
              <div className="col-sm-12">
                <h1 className="text-center mt-5">Alejandro Maruri</h1>
              </div>
            </div>

            <div className="row mt-5">
              <div className="col-sm-12 col-lg-4">
                <img className="mimg d-block mx-auto" src={Me} alt="Me"></img>
              </div>

              <div className="col-sm-12 col-lg-8">
                <p>
                  Hey there! I'm Alejandro.
                  <br></br> <br></br>I am a <strong>university student</strong>{" "}
                  of <em>Systems Engineering</em> degree, at the{" "}
                  <strong>
                    <em title="Universidad San Francisco de Quito">
                      <a href="http://www.usfq.edu.ec/Paginas/Inicio.aspx">
                        USFQ
                      </a>
                    </em>
                  </strong>
                  .<br></br> <br></br>
                  As an organized, effective, responsible, and highly motivated
                  to learn person, I am able to <strong>adapt</strong> to
                  circumstances, work as a <strong>team</strong> and be{" "}
                  <strong>autodidact</strong>.<br></br> <br></br>I offer
                  different kind of works in <strong>developmente area</strong>.
                  So, let's check my{" "}
                  <strong>
                    <em>
                      <a
                        title="Portfolio"
                        href="./pages/portfolio/portfolio.html"
                      >
                        portfolio
                      </a>
                    </em>
                  </strong>
                  , and, if <em>you need something</em>, do not hesitate to{" "}
                  <strong>
                    <em>
                      <a
                        type="button"
                        data-toggle="modal"
                        data-target="#modalForm"
                        title="Get in touch"
                        href="#"
                      >
                        get in touch
                      </a>
                    </em>
                  </strong>
                  .
                </p>
              </div>
            </div>
          </div>
        </main>

        <Footer />
      </div>
    );
  }
}


export default Home;