@AssetAllocation
Feature: Asset Allocation Component Feature file (User story - INTL-6436)
As a user i want to see the asset allocation of my portfolio

  @component
  Scenario: Verify Asset Allocation component visible columns
    When I navigates to "asset-allocation (portfolio-analysis)" inside "Business-components" menu
    Then Data Table for Asset Allocation on "Business-components" should have "Asset" and "% Net" visible columns

  @component
  Scenario: Verify Asset Allocation component visible columns
    When I navigates to "asset-allocation (portfolio-analysis)" inside "Business-components" menu
    Then Data Table for Asset Allocation on "Business-components" should have "% Bmark" visible column

  @xray @fidelity
  Scenario: Verify Asset Allocation component visible columns for Fidelity Xray 1
    When I navigates to "Fidelity" tab inside "xray (portfolio-analysis)" inside "Tool-components" menu
    
  @xray @fidelity
  Scenario: Verify Asset Allocation component visible columns for Fidelity Xray 2
    When I navigates to "Fidelity" tab inside "xray (portfolio-analysis)" inside "Tool-components" menu
    Then Data Table for Asset Allocation on "FidelityXray" should have "Bmark (%)" visible column
