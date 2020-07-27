import React from "react";
import "./contact-form.css";

class ContactForm extends React.Component {
  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-sm-12">
            <form id="mContactForm" className="about-form maboutform">
              <div className="container">
                <div className="row">
                  <div className="col">
                    <input
                      id="modal_txt_email"
                      type="email"
                      className="form-control w-75"
                      placeholder="Your Email"
                    ></input>
                  </div>
                  <div className="col">
                    <input
                      id="modal_txt_name"
                      type="text"
                      className="form-control"
                      placeholder="Your Name"
                    ></input>
                  </div>
                </div>

                <div className="row mt-3">
                  <div className="col">
                    <div className="form-group">
                      <input
                        id="modal_txt_topic"
                        type="text"
                        className="form-control w-75"
                        placeholder="Topic"
                      ></input>
                    </div>
                  </div>

                  <div className="col"></div>
                </div>

                <div className="row">
                  <div className="col">
                    <div className="form-group">
                      <textarea
                        id="modal_txt_request"
                        className="form-control"
                        name=""
                        rows="5"
                        placeholder="Your Request..."
                      ></textarea>
                    </div>
                  </div>
                </div>

                <div className="row">
                  <div className="col-sm-12">
                    <button
                      id="modal_btn_send"
                      type="button"
                      className="btn-border-black"
                    >
                      Send
                    </button>
                  </div>
                </div>

                <div className="row">
                  <div className="col margin-top-sm">
                    <small id="success-form-message-modal"></small>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default ContactForm;
