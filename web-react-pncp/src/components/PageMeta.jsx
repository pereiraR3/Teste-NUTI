// src/components/PageMeta.jsx
import React from 'react';
import { Helmet } from 'react-helmet';

const PageMeta = ({ title, favicon }) => {
  return (
    <Helmet>
      <title>{title}</title>
      <link rel="icon" type="image/png" href={favicon} />
    </Helmet>
  );
};

export default PageMeta;
